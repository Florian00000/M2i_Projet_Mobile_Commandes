package org.example.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.dto.order.OrderDtoPost;
import org.example.entity.Order;
import org.example.service.OrderService;

@Path("/api/orders")
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    OrderService orderService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrder(OrderDtoPost orderDtoPost) {
//        try {
            Order order = orderService.addOrder(orderDtoPost);
            return Response.status(Response.Status.CREATED).entity(order).build();
//        }catch (IllegalArgumentException e ) {
//            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
//        }

    }

}
