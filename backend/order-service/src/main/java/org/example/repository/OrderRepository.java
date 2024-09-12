package org.example.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.example.entity.Order;

@ApplicationScoped
public class OrderRepository implements PanacheMongoRepository<Order> {
}
