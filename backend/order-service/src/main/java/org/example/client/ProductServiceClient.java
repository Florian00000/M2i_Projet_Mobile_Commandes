package org.example.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.dto.product.ProductDto;

@Path("/api/products")
@RegisterRestClient(configKey = "product_service")
public interface ProductServiceClient {

    @GET
    @Path("/{id}")
    public ProductDto getProductById(@PathParam("id") long id);

    @PATCH
    @Path("/{id}/down-stock/{stock}")
    public Response downProductStock(@PathParam("id") long id, @PathParam("stock") int stock);
}
