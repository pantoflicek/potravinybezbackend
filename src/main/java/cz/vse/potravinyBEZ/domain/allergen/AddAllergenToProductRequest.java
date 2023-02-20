package cz.vse.potravinyBEZ.domain.allergen;

//Persistence
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

//Lombok
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddAllergenToProductRequest {
    @NotBlank
    @Length(min = 2, max = 200)
    private String product;

    @NotBlank
    @Length(min = 2, max = 45)
    private String allergen;
}
