package org.example.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.dto.category.CategoryDtoPost;
import org.example.service.CategoryService;

@Path("/api/category")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {

    @Inject
    CategoryService categoryService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCategory(CategoryDtoPost categoryDtoPost) {
        return Response.status(Response.Status.CREATED).entity(categoryService.createCategory(categoryDtoPost)).build();
    }
}
