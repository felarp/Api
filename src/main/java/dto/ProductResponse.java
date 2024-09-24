package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data


public class ProductResponse {

    List<Cart> cart;

    @JsonProperty("product_id")
    int productId;

    @JsonProperty ("quantity")
    int quantity;

    public ProductResponse(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

}




