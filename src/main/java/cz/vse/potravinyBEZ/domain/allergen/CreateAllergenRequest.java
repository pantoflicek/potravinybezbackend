package cz.vse.potravinyBEZ.domain.allergen;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAllergenRequest {
    @NotBlank
    @Length(min = 2, max = 45)
    private String name;
}
