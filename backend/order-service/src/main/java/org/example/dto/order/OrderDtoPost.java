package org.example.dto.order;

import lombok.Data;
import org.example.dto.productOrder.ProductOrderDtoPost;
import org.example.utils.DeliveryType;

import java.util.List;

@Data
public class OrderDtoPost {

    private DeliveryType deliveryType;
    private List<ProductOrderDtoPost> products;
}
