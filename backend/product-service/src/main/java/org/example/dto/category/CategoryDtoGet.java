package org.example.dto.category;

import lombok.Data;
import org.example.dto.product.ProductDtoGetForCategory;
import org.example.entity.Category;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDtoGet {

    private long id;
    private String name;

    private List<ProductDtoGetForCategory> products;

    public CategoryDtoGet(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.products = category.getProducts() != null?
                category.getProducts().stream().map(ProductDtoGetForCategory::new).toList()
                : new ArrayList<>();
    }
}
