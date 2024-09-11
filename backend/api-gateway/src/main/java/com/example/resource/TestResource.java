package com.example.resource;

import com.example.interceptor.ProtectedPath;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@ApplicationScoped
public class TestResource {

    @GET
    @Path("/authenticated")
//    @ProtectedPath
    public Response authenticated() {
        return Response.ok("{\"message\": \"Authentication OK\"}").build();
    }
}
