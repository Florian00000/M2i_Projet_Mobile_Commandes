package org.example.dto.category;

import lombok.Data;
import org.example.entity.Category;

@Data
public class CategoryDtoPost {
    private String name;

    public Category toCategory() {
        return Category.builder()
                .name(name)
                .build();
    }
}
