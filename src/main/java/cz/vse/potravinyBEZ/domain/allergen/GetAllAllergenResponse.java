package cz.vse.potravinyBEZ.domain.allergen;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllAllergenResponse {
    private List<Allergen> allergens;
}
