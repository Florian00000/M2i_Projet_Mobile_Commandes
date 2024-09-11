package org.example.dto.product;

import lombok.Data;
import org.example.dto.category.CategoryDto;

import java.util.List;

@Data
public class ProductDto {

    private long id;
    private String name;
    private int stock;
    private String description;
    private double price;

    private List<CategoryDto> categories;
}
