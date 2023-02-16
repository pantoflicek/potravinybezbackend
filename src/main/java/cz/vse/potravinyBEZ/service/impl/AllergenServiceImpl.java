package cz.vse.potravinyBEZ.service.impl;

import cz.vse.potravinyBEZ.domain.allergen.*;
import cz.vse.potravinyBEZ.domain.converter.EntityToAllergenConverter;
import cz.vse.potravinyBEZ.repository.AllergenRepo;
import cz.vse.potravinyBEZ.repository.entity.AllergenEntity;
import cz.vse.potravinyBEZ.service.AllergenService;

//Lombok
import lombok.AllArgsConstructor;

//Spring
import org.springframework.stereotype.Service;

//Java
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AllergenServiceImpl implements AllergenService {
    private final AllergenRepo allergenRepo;

    @Override
    public CreateAllergenResponse createAllergen(CreateAllergenRequest allergen) {
        AllergenEntity newAllergen = AllergenEntity.builder()
                .name(allergen.getName())
                .build();
        AllergenEntity isExisting = allergenRepo.findByNameIsLike(newAllergen.getName());
        if (Objects.isNull(isExisting)){
            allergenRepo.save(newAllergen);
            return CreateAllergenResponse.builder()
                    .response("Allergen: " + newAllergen.getName() + " has been created!")
                    .build();
        } else {
            return CreateAllergenResponse.builder()
                    .response("Allergen already exists!")
                    .build();
        }
    }

    @Override
    public GetAllAllergenResponse getAllAllergens() {
        List<AllergenEntity> allAllergenEntities = allergenRepo.findAll();
        List<Allergen> allAllergens = allAllergenEntities.stream()
                .map(EntityToAllergenConverter::convert)
                .toList();
        return GetAllAllergenResponse.builder()
                .allergens(allAllergens)
                .build();
    }

    @Override
    public DeleteAllergenResponse deleteAllergen(DeleteAllergenRequest allergen) {
        AllergenEntity allergenToDelete = allergenRepo.findByNameIsLike(allergen.getName());
        if (Objects.isNull(allergenToDelete)){
            return DeleteAllergenResponse.builder()
                    .response("Specified allergen does not exists!")
                    .build();
        } else {
            allergenRepo.delete(allergenToDelete);
            return DeleteAllergenResponse.builder()
                    .response("Allergen: " + allergenToDelete.getName() + " has been deleted!")
                    .build();
        }
    }
}
