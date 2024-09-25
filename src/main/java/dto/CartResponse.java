package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;


@NoArgsConstructor (force = true)
@Value

public class CartResponse {
    @JsonProperty("cart")
    List<Cart> cart;

    @JsonProperty("total_price")
    double totalPrice;

    @JsonProperty("total_discount")
    double totalDiscount;
}
