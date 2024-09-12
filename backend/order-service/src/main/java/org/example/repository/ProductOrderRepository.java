package org.example.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.example.entity.ProductOrder;

@ApplicationScoped
public class ProductOrderRepository implements PanacheMongoRepository<ProductOrder> {
}
