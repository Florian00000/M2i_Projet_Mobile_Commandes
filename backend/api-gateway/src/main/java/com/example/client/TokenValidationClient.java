package com.example.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/validation")
@RegisterRestClient(configKey = "authentication-service")
public interface TokenValidationClient {
    @GET
    Response validateToken(@HeaderParam("Authorization") String bearerToken);
}
