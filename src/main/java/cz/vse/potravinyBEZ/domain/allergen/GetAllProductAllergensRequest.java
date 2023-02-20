package cz.vse.potravinyBEZ.domain.allergen;

//Persistence
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

//Lombok
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductAllergensRequest {
    @NotBlank
    @Length(min = 2, max = 200)
    private String product;
}
