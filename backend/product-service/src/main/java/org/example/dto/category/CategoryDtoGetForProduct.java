package org.example.dto.category;

import lombok.Data;
import org.example.entity.Category;

@Data
public class CategoryDtoGetForProduct {

    private long id;
    private String name;


    public CategoryDtoGetForProduct(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
