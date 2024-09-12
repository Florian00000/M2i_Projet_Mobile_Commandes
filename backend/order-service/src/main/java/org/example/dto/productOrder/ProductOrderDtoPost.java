package org.example.dto.productOrder;

import lombok.Data;
import org.example.entity.ProductOrder;

@Data
public class ProductOrderDtoPost {
    private long productId;
    private int quantity;

    public ProductOrder toProductOrder(){
        ProductOrder productOrder = new ProductOrder();
        productOrder.setProductId(this.productId);
        productOrder.setQuantity(this.quantity);
        return productOrder;
    }

}



