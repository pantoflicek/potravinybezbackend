package cz.vse.potravinyBEZ.service.impl;

import cz.vse.potravinyBEZ.domain.allergen.*;
import cz.vse.potravinyBEZ.domain.converter.EntityToAllergenConverter;
import cz.vse.potravinyBEZ.repository.AllergenRepo;
import cz.vse.potravinyBEZ.repository.ProductAllergenRepo;
import cz.vse.potravinyBEZ.repository.ProductRepo;
import cz.vse.potravinyBEZ.repository.entity.AllergenEntity;
import cz.vse.potravinyBEZ.repository.entity.ProductAllergenEntity;
import cz.vse.potravinyBEZ.repository.entity.ProductEntity;
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
    private final ProductRepo productRepo;
    private final ProductAllergenRepo productAllergenRepo;

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

    @Override
    public AddAllergenToProductResponse addAllergenToProduct(AddAllergenToProductRequest request) {
        AllergenEntity addingAllergen = allergenRepo.findByNameIsLike(request.getAllergen());
        ProductEntity addingProduct = productRepo.findByNameIsLike(request.getProduct());
        ProductAllergenEntity productAllergenEntity = ProductAllergenEntity.builder()
                .allergen(addingAllergen)
                .product(addingProduct)
                .build();
        if (addingProduct == null){
            return AddAllergenToProductResponse.builder()
                    .response("Specified product does not exists!")
                    .build();
        } else if (addingAllergen == null){
            return AddAllergenToProductResponse.builder()
                    .response("Specified allergen does not exists!")
                    .build();
        } else {
            ProductAllergenEntity isExisting = productAllergenRepo.findByProductAndAllergenId(addingProduct.getId(), addingAllergen.getId());
            if (Objects.isNull(isExisting)){
                productAllergenRepo.save(productAllergenEntity);
                return AddAllergenToProductResponse.builder()
                        .response("Allergen: " + addingAllergen.getName() + " has been added to product: " + addingProduct.getName() + "!")
                        .build();
            } else {
                return AddAllergenToProductResponse.builder()
                        .response("Specified product already has this allergen!")
                        .build();
            }
        }
    }

    @Override
    public DeleteProductAllergenResponse deleteProductAllergen(DeleteProductAllergenRequest request) {
        AllergenEntity deletingAllergen = allergenRepo.findByNameIsLike(request.getAllergen());
        ProductEntity deletingProduct = productRepo.findByNameIsLike(request.getProduct());
        if (deletingProduct == null){
            return DeleteProductAllergenResponse.builder()
                    .response("Specified product does not exists!")
                    .build();
        } else if (deletingAllergen == null){
            return DeleteProductAllergenResponse.builder()
                    .response("Specified allergen does not exists!")
                    .build();
        } else {
            ProductAllergenEntity entityToDelete = productAllergenRepo.findByProductAndAllergenId(deletingProduct.getId(), deletingAllergen.getId());
            if (Objects.isNull(entityToDelete)){
                return DeleteProductAllergenResponse.builder()
                        .response("Product does not have the specified allergen!")
                        .build();
            } else {
                productAllergenRepo.save(entityToDelete);
                return DeleteProductAllergenResponse.builder()
                        .response("Allergen: " + deletingAllergen.getName() + " was deleted from product: " + deletingProduct.getName())
                        .build();
            }
        }
    }

    @Override
    public GetAllProductAllergensResponse getAllProductAllergen(GetAllProductAllergensRequest request) {
        ProductEntity product = productRepo.findByNameIsLike(request.getProduct());
        List<String> list = productAllergenRepo.findAllAllergensByProductIdIsLike(product.getId());
        return GetAllProductAllergensResponse.builder()
                .allergens(list)
                .build();
    }
}
