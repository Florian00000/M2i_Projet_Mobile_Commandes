package org.example.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.example.entity.Category;

@ApplicationScoped
public class CategoryRepository implements PanacheRepository<Category> {
}
