package com.example.dto.productdto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    private long id;
    private String name;
    private int stock;
    private String description;
    private double price;

    private List<Long> listProductIds;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<CategoryDTO> categories;
}
