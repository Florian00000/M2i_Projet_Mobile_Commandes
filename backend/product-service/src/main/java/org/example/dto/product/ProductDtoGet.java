package org.example.dto.product;

import lombok.Data;
import org.example.dto.category.CategoryDtoGetForProduct;
import org.example.entity.Category;
import org.example.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDtoGet {

    private long id;
    private String name;
    private int stock;
    private String description;
    private double price;
    private List<CategoryDtoGetForProduct> categories;

    public ProductDtoGet(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.stock = product.getStock();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.categories = product.getCategories() != null ? product.getCategories().stream().map(CategoryDtoGetForProduct::new).toList() : new ArrayList<>();
    }
}
