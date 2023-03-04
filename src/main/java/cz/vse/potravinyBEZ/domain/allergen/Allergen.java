package cz.vse.potravinyBEZ.domain.allergen;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Allergen {
    private int id;
    private String name;
}
