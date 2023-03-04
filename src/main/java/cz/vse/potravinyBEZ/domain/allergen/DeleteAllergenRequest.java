package cz.vse.potravinyBEZ.domain.allergen;

//Persistence
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotBlank;

//Lombok
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteAllergenRequest {
    @NotBlank
    @Length(min = 2, max = 45)
    private String name;
}
