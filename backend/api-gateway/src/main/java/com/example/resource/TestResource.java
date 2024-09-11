package com.example.resource;

import com.example.filter.ProtectedPath;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestResource {

    @GET
    @Path("/not-authenticated")
    public Response notAuthenticated() {
        return Response.ok("{\"message\": \"public path OK\"}").build();
    }

    @GET
    @Path("/authenticated")
    @ProtectedPath
    public Response authenticated() {
        return Response.ok("{\"message\": \"protected path ok\"}").build();
    }
}
