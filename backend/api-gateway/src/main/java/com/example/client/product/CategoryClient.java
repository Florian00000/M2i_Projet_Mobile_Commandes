package com.example.client.product;

import com.example.dto.productdto.CategoryDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/category")
@RegisterRestClient(configKey = "product-service")
public interface CategoryClient {

    @GET
    Response getAllCategories();

    @GET
    @Path("/{id}")
    Response getCategoryById(@PathParam("id") long id);

    @POST
    Response addCategory(CategoryDTO category);

    @PATCH
    @Path("/{id}")
    Response updateCategory(@PathParam("id") long id, CategoryDTO category);

    @DELETE
    @Path("/{id}")
    Response deleteCategory(@PathParam("id") long id);

}
