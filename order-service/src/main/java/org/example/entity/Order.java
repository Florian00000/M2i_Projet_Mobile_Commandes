package org.example.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.example.utils.DeliveryState;
import org.example.utils.DeliveryType;

import java.util.ArrayList;
import java.util.List;


@MongoEntity(collection = "command")
@Getter
@Setter
public class Order extends PanacheMongoEntity {

    //private ObjectId idOder;
    private DeliveryState deliveryState;
    private DeliveryType deliveryType;

    private List<ObjectId> products;

    public Order(DeliveryState deliveryState, DeliveryType deliveryType) {
        this.deliveryState = deliveryState;
        this.deliveryType = deliveryType;
        this.products = new ArrayList<>();
    }

    public void addProduct(ObjectId productId) {
        this.products.add(productId);
    }
}
