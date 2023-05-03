package cz.vse.potravinyBEZ.domain.product;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindByAllergenResponse {
    private List<Product> products;
}
