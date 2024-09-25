package dto;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Value

public class Cart {

    int id;

    String name;

    String category;

    double discount;

    double price;
}
