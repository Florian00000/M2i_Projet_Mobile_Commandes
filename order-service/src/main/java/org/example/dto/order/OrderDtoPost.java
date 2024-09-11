package org.example.dto.order;

import lombok.Data;
import org.example.entity.ProductOrder;
import org.example.utils.DeliveryState;
import org.example.utils.DeliveryType;

import java.util.List;

@Data
public class OrderDtoPost {

    private DeliveryState deliveryState;
    private DeliveryType deliveryType;

    private List<ProductOrder> products;
}
