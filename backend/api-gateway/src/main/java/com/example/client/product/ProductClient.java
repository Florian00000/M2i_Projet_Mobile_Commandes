package com.example.client.product;

import com.example.dto.productdto.ProductDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/product")
@RegisterRestClient(configKey = "product-service")
public interface ProductClient {

    @GET
    Response getAllProducts();

    @GET
    @Path("/{id}")
    Response getProduct(@PathParam("id") long id);

    @POST
    Response addProduct(ProductDTO product);

    @PUT
    @Path("/{id}")
    Response updateProduct(@PathParam("id") long id, ProductDTO product);

    @DELETE
    @Path("/{id}")
    Response deleteProduct(@PathParam("id") long id);

    @PATCH
    @Path("/{id}/up-stock/{stock}")
    Response upStock(@PathParam("id") long id, @PathParam("stock") int stock);

    @PATCH
    @Path("/{id}/down-Stock/{stock}")
    Response downProductStock(@PathParam("id") long id, @PathParam("stock") int stock);

}
