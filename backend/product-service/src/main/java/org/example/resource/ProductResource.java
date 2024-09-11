package org.example.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.dto.product.ProductDtoGet;
import org.example.dto.product.ProductDtoPost;
import org.example.service.ProductService;

@Path("/api/product")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService productService;

    @GET
    public Response getAllProducts() {
        return Response.ok(productService.getAllProducts()).build();
    }

    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") long id) {
        ProductDtoGet productDtoGet =  productService.getProductById(id);
        if (productDtoGet != null) {
            return Response.ok(productDtoGet).build();
        }else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(ProductDtoPost productDtoPost) {
        return Response.status(Response.Status.CREATED).entity(productService.createProduct(productDtoPost)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") long id, ProductDtoPost productDtoPost) {
        ProductDtoGet productDtoGet = productService.getProductById(id);
        if (productDtoGet != null) {
            return Response.ok(productService.updateProduct(id, productDtoPost)).build();
        }else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") long id) {
        boolean success = productService.deleteProduct(id);
        if (success) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PATCH
    @Path("/{id}/up-stock/{stock}")
    public Response upProductStock(@PathParam("id") long id, @PathParam("stock") int stock) {
        ProductDtoGet productDtoGet = productService.upStock(id, stock);
        if (productDtoGet != null) {
            return Response.ok(productDtoGet).build();
        }else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PATCH
    @Path("/{id}/down-Stock/{stock}")
    public Response downProductStock(@PathParam("id") long id, @PathParam("stock") int stock) {
        try {
            ProductDtoGet productDtoGet = productService.downStock(id, stock);
            if (productDtoGet != null) {
                return Response.ok(productDtoGet).build();
            }else return Response.status(Response.Status.NOT_FOUND).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST)..build();
        }


    }
}
