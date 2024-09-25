package dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;
import lombok.Value;


@NoArgsConstructor(force = true)
@Value

public class Cart {

    int id;

    String name;

    String category;

    double discount;

    double price;
}
