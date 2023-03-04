package cz.vse.potravinyBEZ.domain.converter;

import cz.vse.potravinyBEZ.domain.allergen.Allergen;
import cz.vse.potravinyBEZ.repository.entity.AllergenEntity;

public final class EntityToAllergenConverter {
    public EntityToAllergenConverter(){
    }
    public static Allergen convert(AllergenEntity allergenEntity){
        return Allergen.builder()
                .id(allergenEntity.getId())
                .name(allergenEntity.getName())
                .build();
    }
}
