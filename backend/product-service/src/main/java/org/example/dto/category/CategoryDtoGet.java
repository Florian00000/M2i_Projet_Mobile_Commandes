package org.example.dto.category;

import lombok.Data;
import org.example.entity.Category;
import org.example.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDtoGet {

    private long id;
    private String name;

    private List<Product> products;

    public CategoryDtoGet(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.products = category.getProducts() != null? category.getProducts() : new ArrayList<>();
    }
}
