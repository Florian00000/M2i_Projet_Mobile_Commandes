package com.example.resource;

import com.example.client.authentication.AuthenticationClient;
import com.example.dto.authdto.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationResource {

    @Inject @RestClient
    AuthenticationClient authenticationClient;

    @POST
    @Path("/register")
    public Response register(UserDTO userDTO) throws JsonProcessingException {
        return Response.status(Response.Status.CREATED)
                .entity(authenticationClient.register(userDTO)).build();
    }

    @POST
    @Path("/login")
    public Response login(UserDTO userDTO) {
        return Response.ok(authenticationClient.login(userDTO)).build();
    }
}
