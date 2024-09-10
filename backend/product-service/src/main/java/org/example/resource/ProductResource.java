package org.example.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.dto.product.ProductDtoPost;
import org.example.service.ProductService;

@Path("/api/product")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService productService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(ProductDtoPost productDtoPost) {
        return Response.status(Response.Status.CREATED).entity(productService.createProduct(productDtoPost)).build();
    }
}
