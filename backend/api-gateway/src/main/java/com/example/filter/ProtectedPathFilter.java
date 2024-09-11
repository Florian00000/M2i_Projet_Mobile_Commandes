package com.example.filter;

import com.example.client.authentication.TokenValidationClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.io.IOException;


@Provider
@ProtectedPath
@ApplicationScoped
public class ProtectedPathFilter implements ContainerRequestFilter {

    @Inject @RestClient
    TokenValidationClient tokenValidationClient;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        try {
            String bearerToken = containerRequestContext.getHeaders().getFirst("Authorization");
            tokenValidationClient.validateToken(bearerToken);
        } catch (Exception e) {
            throw new ForbiddenException(
                    "Token invalid",
                    Response.status(Response.Status.FORBIDDEN).entity("{\"error\":\"Token invalid\"}").build()
            );
        }

    }
}
