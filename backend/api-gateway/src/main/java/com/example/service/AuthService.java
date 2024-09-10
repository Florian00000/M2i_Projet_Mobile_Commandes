package com.example.service;

import com.example.client.AuthenticationClient;
import com.example.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.io.File;

@ApplicationScoped
public class AuthService {
    @Inject
    @RestClient
    AuthenticationClient authenticationClient;

    public UserDTO registerUser(UserDTO user) {
        try {
            ObjectMapper mapper = new ObjectMapper();
//            mapper.writeValueAsString(user);
            return authenticationClient.register(mapper.writeValueAsString(user));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
