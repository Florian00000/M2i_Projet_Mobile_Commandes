package org.example.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.example.dto.order.OrderDtoPost;
import org.example.entity.Order;
import org.example.service.OrderService;
import org.example.utils.DeliveryState;

@Path("/api/orders")
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    OrderService orderService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrder(OrderDtoPost orderDtoPost) {
            Order order = orderService.addOrder(orderDtoPost);
            return Response.status(Response.Status.CREATED).entity(order).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOrder(@PathParam("id")String id, OrderDtoPost orderDtoPost) {
        return Response.status(Response.Status.OK)
                .entity(orderService.updateOrder(orderDtoPost, new ObjectId(id))).build();
    }

    @PATCH
    @Path("/sent-order/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setOrderToDeliverySent(@PathParam("id")String id){
        return Response.ok(orderService.changeDeliveryState(DeliveryState.SENT, new ObjectId(id))).build();
    }

    @PATCH
    @Path("/delivered-order/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setOrderToDeliveryDelivered(@PathParam("id")String id){
        return Response.ok(orderService.changeDeliveryState(DeliveryState.DELIVERED, new ObjectId(id))).build();
    }

    @PATCH
    @Path("/cancel-order/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cancelOrder(@PathParam("id")String id){
        return Response.ok(orderService.changeDeliveryState(DeliveryState.CANCELED, new ObjectId(id))).build();
    }

}
