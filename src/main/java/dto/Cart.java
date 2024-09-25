package dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@Getter
@NoArgsConstructor(force = true)
@Value

public class Cart {

    int id;

    String name;

    String category;

    double discount;

    double price;
}
