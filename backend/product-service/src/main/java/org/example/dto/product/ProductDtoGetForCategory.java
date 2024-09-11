package org.example.dto.product;

import lombok.Data;
import org.example.entity.Product;


@Data
public class ProductDtoGetForCategory {

    private long id;
    private String name;
    private int stock;
    private String description;
    private double price;

    public ProductDtoGetForCategory(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.stock = product.getStock();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }
}
