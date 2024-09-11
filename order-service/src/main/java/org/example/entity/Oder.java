package org.example.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@MongoEntity(collection = "command")
@Getter
@Setter
public class Oder extends PanacheMongoEntity {

    private ObjectId idOder;
    
}
