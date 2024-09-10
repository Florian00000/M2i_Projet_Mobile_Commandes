package com.example.client;

import com.example.dto.UserDTO;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Map;

@Path("/api/auth")
@RegisterRestClient(configKey = "authentication-service")
public interface AuthenticationClient {

    @POST
    @Path("/register")
    UserDTO register(UserDTO userDTO);

    @POST
    @Path("/login")
    Map<String, String> login(UserDTO userDTO);

}
