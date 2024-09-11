package com.example.dto.productdto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    private String name;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<ProductDTO> products;
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private List<Long> listProductIds;

}
