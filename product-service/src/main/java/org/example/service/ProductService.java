package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.repository.ProductRepository;

@ApplicationScoped
public class ProductService {


    @Inject
    ProductRepository productRepository;


}
