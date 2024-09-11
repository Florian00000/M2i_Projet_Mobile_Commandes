package com.example.resource;

import com.example.client.product.CategoryClient;
import com.example.dto.productdto.CategoryDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/categories")
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {

    @Inject @RestClient
    CategoryClient categoryClient;

    @GET
    public Response getAllCategories() {
        return categoryClient.getAllCategories();
    }

    @GET
    @Path("/{id}")
    public Response getCategoryById(@PathParam("id") long id) {
        return categoryClient.getCategoryById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCategory(CategoryDTO category) {
        return categoryClient.addCategory(category);
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCategory(@PathParam("id") long id, CategoryDTO category) {
        return categoryClient.updateCategory(id, category);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCategory(@PathParam("id") long id) {
        return categoryClient.deleteCategory(id);
    }
}
