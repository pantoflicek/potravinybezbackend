package cz.vse.potravinyBEZ.domain.product;

//Lombok
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindByAllergenRequest {
    private int allergenId;
}
