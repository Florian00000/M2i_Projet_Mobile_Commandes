package org.example.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.dto.category.CategoryDtoGet;
import org.example.dto.category.CategoryDtoPost;
import org.example.service.CategoryService;

@Path("/api/categories")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {

    @Inject
    CategoryService categoryService;

    @GET
    public Response getAllCategories() {
        return Response.ok(categoryService.getAllCategories()).build();
    }

    @GET
    @Path("/{id}")
    public Response getCategoryById(@PathParam("id") long id) {
        CategoryDtoGet categoryDtoGet = categoryService.getCategoryById(id);
        if (categoryDtoGet != null) {
            return Response.ok(categoryDtoGet).build();
        }else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCategory(CategoryDtoPost categoryDtoPost) {
        return Response.status(Response.Status.CREATED).entity(categoryService.createCategory(categoryDtoPost)).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCategory(@PathParam("id") long id, CategoryDtoPost categoryDtoPost) {
        CategoryDtoGet categoryDtoGet = categoryService.updateCategory(categoryDtoPost, id);
        if (categoryDtoGet != null) {
            return Response.ok(categoryDtoGet).build();
        }else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCategory(@PathParam("id") long id) {
        boolean result = categoryService.deleteCategory(id);
        if (result) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }else return Response.status(Response.Status.NOT_FOUND).build();
    }

}
