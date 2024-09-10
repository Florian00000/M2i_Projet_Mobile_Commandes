package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.example.dto.product.ProductDtoGet;
import org.example.dto.product.ProductDtoPost;
import org.example.entity.Category;
import org.example.entity.Product;
import org.example.repository.CategoryRepository;
import org.example.repository.ProductRepository;

import java.util.List;

@ApplicationScoped
public class ProductService {


    @Inject
    ProductRepository productRepository;

    @Inject
    CategoryRepository categoryRepository;

    //TODO Gérer les problèmes de JSON ignore avec un nouveau dto

    @Transactional
    public ProductDtoGet createProduct(ProductDtoPost productDtoPost) {
        Product product = convertProductDtoToProduct(productDtoPost);
        productRepository.persist(product);
        return new ProductDtoGet(product);
    }

    private Product convertProductDtoToProduct(ProductDtoPost productDtoPost) {
        List<Category> categories = productDtoPost.getListProductIds().stream().map(id -> categoryRepository.findById(id)).toList();
        return Product.builder()
                .name(productDtoPost.getName())
                .stock(productDtoPost.getStock())
                .description(productDtoPost.getDescription())
                .price(productDtoPost.getPrice())
                .categories(categories)
                .build();

    }
}
