package dto;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Value;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)

@NoArgsConstructor (force = true)
@Value

public class Cart {

    int id;

    String name;

    String category;

    double discount;

    double price;

    int quantity;
}