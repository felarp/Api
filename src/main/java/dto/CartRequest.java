package dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
@Getter

public class CartRequest {

    @JsonProperty("product_id")
    int productId;

    @JsonProperty ("quantity")
    int quantity;

    public CartRequest(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

}

