package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int stock;
    private String description;
    private double price;

    //TODO voir si relation Many to many ou 2 service
//    @ManyToOne
//    @JoinColumn(name = "category_id")
//    private Category category;
}
