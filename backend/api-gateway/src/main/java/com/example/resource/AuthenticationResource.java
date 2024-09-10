package com.example.resource;

import com.example.client.AuthenticationClient;
import com.example.dto.UserDTO;
import com.example.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Inject
    AuthService authService;

    @POST
    @Path("/register")
    public Response register(UserDTO userDTO) throws JsonProcessingException {
        return Response.status(Response.Status.CREATED)
                .entity(authService.registerUser(userDTO)).build();
    }

    @POST
    @Path("/login")
    public Response login(UserDTO userDTO) {
        return Response.ok(/*authenticationClient.login(userDTO)*/).build();
    }
}
