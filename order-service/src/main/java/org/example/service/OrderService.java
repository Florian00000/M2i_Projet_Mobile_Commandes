package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.client.ProductServiceClient;
import org.example.dto.order.OrderDtoPost;
import org.example.dto.product.ProductDto;
import org.example.entity.Order;
import org.example.entity.ProductOrder;
import org.example.repository.OrderRepository;
import org.example.repository.ProductOrderRepository;

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
        for (ProductOrder productOrder : orderDtoPost.getProducts()){
            try {
                productServiceClient.getProductById(productOrder.getProductId());
            }catch (Exception e){
                throw new BadRequestException(e.getMessage(), Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\":\"Product Not found\"}").build());
            }

            productOrderRepository.persist(productOrder);
            order.addProduct(productOrder.id);

        }

        orderRepository.persist(order);
        return order;
    }


    private Order toOrder(OrderDtoPost orderDtoPost) {
        return new Order(orderDtoPost.getDeliveryState(), orderDtoPost.getDeliveryType());
    }

}
