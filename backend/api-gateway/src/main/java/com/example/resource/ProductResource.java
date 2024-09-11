package com.example.resource;

import com.example.client.product.ProductClient;
import com.example.dto.productdto.ProductDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject @RestClient
    ProductClient productClient;

    @GET
    public Response getAllProducts() {
        return productClient.getAllProducts();
    }

    @GET
    @Path("/{id}")
    public Response getProduct(@PathParam("id") long id) {
        return productClient.getProduct(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(ProductDTO product) {
        return productClient.addProduct(product);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") long id, ProductDTO product) {
        return productClient.updateProduct(id, product);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") long id) {
        return productClient.deleteProduct(id);
    }

    @PATCH
    @Path("/{id}/up-stock/{stock}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response upStock(@PathParam("id") long id, @PathParam("stock") int stock) {
        return productClient.upStock(id, stock);
    }

    @PATCH
    @Path("/{id}/down-Stock/{stock}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response downProductStock(@PathParam("id") long id, @PathParam("stock") int stock) {
        return productClient.downProductStock(id, stock);
    }

}
