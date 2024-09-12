package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.client.ProductServiceClient;
import org.example.dto.order.OrderDtoPost;
import org.example.dto.product.ProductDto;
import org.example.dto.productOrder.ProductOrderDtoPost;
import org.example.entity.Order;
import org.example.entity.ProductOrder;
import org.example.repository.OrderRepository;
import org.example.repository.ProductOrderRepository;
import org.example.utils.DeliveryState;

import java.util.Optional;

@ApplicationScoped
public class OrderService {

    @Inject
    OrderRepository orderRepository;

    @Inject
    ProductOrderRepository productOrderRepository;

    @Inject
    @RestClient
    ProductServiceClient productServiceClient;

    public Order addOrder(OrderDtoPost orderDtoPost) {
        Order order = toOrder(orderDtoPost);
        enrichOrderWithProducts(order, orderDtoPost);
        orderRepository.persist(order);
        return order;
    }

    public Order updateOrder(OrderDtoPost orderDtoPost, ObjectId orderId) {
        Order order = findOrderById(orderId);
        if (order.getDeliveryState().equals(DeliveryState.IN_PROGRESS)) {
            for (ObjectId productId : order.getProducts()) {
                productOrderRepository.deleteById(productId);
            }
            order.getProducts().clear();
            enrichOrderWithProducts(order, orderDtoPost);
            orderRepository.update(order);
            return order;
        }else {
            throw new BadRequestException("", Response.status(Response.Status.BAD_REQUEST)
                            .entity("\"{\"error\":\"Bad status for order\"}\"").build());
        }

    }

    public Order changeDeliveryState(DeliveryState deliveryState, ObjectId id) {
        Order order = findOrderById(id);
        switch (deliveryState) {
            case SENT:
                if (order.getDeliveryState().equals(DeliveryState.SENT)
                        || order.getDeliveryState().equals(DeliveryState.DELIVERED)
                        || order.getDeliveryState().equals( DeliveryState.CANCELED)) {
                    throw new BadRequestException("",
                            Response.status(Response.Status.BAD_REQUEST)
                                    .entity("\"{\"error\":\"Bad status for order\"}\"").build());
                }
                changeDeliveryStateToSent(order);
                orderRepository.update(order);
                break;
            case DELIVERED:
                if (order.getDeliveryState().equals(DeliveryState.DELIVERED)
                    ||order.getDeliveryState().equals(DeliveryState.IN_PROGRESS)
                    || order.getDeliveryState().equals(DeliveryState.CANCELED)
                ){
                    throw new BadRequestException("",
                            Response.status(Response.Status.BAD_REQUEST)
                                    .entity("\"{\"error\":\"Bad status for order\"}\"").build());
                }
                order.setDeliveryState(DeliveryState.DELIVERED);
                orderRepository.update(order);
                break;
           case CANCELED:
               if (order.getDeliveryState().equals(DeliveryState.CANCELED)
                       ||order.getDeliveryState().equals(DeliveryState.SENT)
                       || order.getDeliveryState().equals(DeliveryState.DELIVERED)
               ){
                   throw new BadRequestException("",
                           Response.status(Response.Status.BAD_REQUEST)
                                   .entity("\"{\"error\":\"Bad status for order\"}\"").build());
               }
               order.setDeliveryState(DeliveryState.CANCELED);
               orderRepository.update(order);
               break;
        }
        return order;
    }


    private Order toOrder(OrderDtoPost orderDtoPost) {
        return new Order(DeliveryState.IN_PROGRESS, orderDtoPost.getDeliveryType());
    }

    private void changeDeliveryStateToSent(Order order) {
        for (ObjectId productId : order.getProducts()){
            ProductOrder productOrder = productOrderRepository.findById(productId);
            ProductDto productDto = productServiceClient.getProductById(productOrder.getProductId());
            try {
                productServiceClient.downProductStock(productDto.getId(), productOrder.getQuantity());
            }catch (Exception e){
                throw new BadRequestException(e.getMessage(), Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\":\"Quantity are not available for idProduct: "
                                + productDto.getId() + "\"}")
                        .build());
            }
        }
        order.setDeliveryState(DeliveryState.SENT);
    }

    private Order findOrderById(ObjectId id) {
        Optional<Order> optional = orderRepository.findByIdOptional(id);
        return optional.orElseThrow(() ->
                new NotFoundException(id.toString(), Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\":\"Order Not found\"}")
                        .build()));
    }

    private void enrichOrderWithProducts(Order order, OrderDtoPost orderDtoPost) {
        for (ProductOrderDtoPost productOrderDtoPost : orderDtoPost.getProducts()){
            try {
                productServiceClient.getProductById(productOrderDtoPost.getProductId());

            }catch (Exception e){
                throw new BadRequestException(e.getMessage(), Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\":\"Product Not found\"}").build());
            }
            if (productOrderDtoPost.getQuantity() >
                    productServiceClient.getProductById(productOrderDtoPost.getProductId()).getStock()){
                throw new BadRequestException("", Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\":\"Quantity are not available\"}").build());
            }
            ProductOrder productOrder = productOrderDtoPost.toProductOrder();
            productOrder.setProductName(productServiceClient.getProductById(productOrderDtoPost.getProductId()).getName());
            productOrderRepository.persist(productOrder);
            order.addProduct(productOrder.id);
        }
    }



}
