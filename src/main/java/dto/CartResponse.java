package dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import java.util.List;


@NoArgsConstructor (force = true)
@Value

public class CartResponse {

    private List<Cart> cart;

    @JsonProperty("total_price")
    double totalPrice;

    @JsonProperty("total_discount")
    double totalDiscount;
}
