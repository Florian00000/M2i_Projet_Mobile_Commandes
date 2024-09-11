package org.example.dto.product;

import lombok.Data;

import java.util.List;

@Data
public class ProductDtoPost {

    private String name;
    private int stock;
    private String description;
    private double price;
    private List<Long> listProductIds;

}
