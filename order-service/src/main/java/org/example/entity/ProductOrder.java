package org.example.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MongoEntity(collection = "product_order")
@Getter
@Setter
@NoArgsConstructor
public class ProductOrder extends PanacheMongoEntity {

    private long productId;
    private String productName;
    private int quantity;
}
