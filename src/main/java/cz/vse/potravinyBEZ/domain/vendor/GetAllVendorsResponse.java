package cz.vse.potravinyBEZ.domain.vendor;

import cz.vse.potravinyBEZ.domain.allergen.Allergen;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllVendorsResponse {
    private List<Allergen> allergens;
}
