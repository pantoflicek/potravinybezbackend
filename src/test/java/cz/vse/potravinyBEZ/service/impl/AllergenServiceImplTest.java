package cz.vse.potravinyBEZ.service.impl;

import cz.vse.potravinyBEZ.domain.allergen.*;
import cz.vse.potravinyBEZ.repository.AllergenRepo;
import cz.vse.potravinyBEZ.repository.ProductAllergenRepo;
import cz.vse.potravinyBEZ.repository.ProductRepo;
import cz.vse.potravinyBEZ.repository.entity.AllergenEntity;
import cz.vse.potravinyBEZ.repository.entity.ProductAllergenEntity;
import cz.vse.potravinyBEZ.repository.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AllergenServiceImplTest {

    @Mock
    private AllergenRepo allergenRepoMock;
    @Mock
    private ProductRepo productRepoMock;
    @Mock
    private ProductAllergenRepo productAllergenRepoMock;

    @InjectMocks
    private AllergenServiceImpl allergenService;

    AllergenEntity allergenEntity = AllergenEntity.builder()
            .id(1)
            .name("allergen")
            .build();

    Allergen allergen = Allergen.builder()
            .id(1)
            .name("allergen")
            .build();

    ProductEntity productEntity = ProductEntity.builder()
            .id(1)
            .name("product")
            .build();

    ProductAllergenEntity productAllergenEntity = ProductAllergenEntity.builder()
            .id(1)
            .product(productEntity)
            .allergen(allergenEntity)
            .build();

    @Test
    void createAllergen() {
        when(allergenRepoMock.findByNameIsLike("allergen")).thenReturn(null);
        CreateAllergenRequest request = CreateAllergenRequest.builder()
                .name("allergen")
                .build();
        CreateAllergenResponse er = CreateAllergenResponse.builder()
                .response("Allergen: allergen has been created!")
                .build();
        CreateAllergenResponse ar = allergenService.createAllergen(request);
        assertEquals(er,ar);
    }

    @Test
    void getAllAllergens() {
        when(allergenRepoMock.findAll()).thenReturn(List.of(allergenEntity));
        GetAllAllergenResponse er = GetAllAllergenResponse.builder()
                .allergens(List.of(allergen))
                .build();
        GetAllAllergenResponse ar = allergenService.getAllAllergens();
        assertEquals(er,ar);
    }

    @Test
    void deleteAllergen() {
        when(allergenRepoMock.findByNameIsLike("allergen")).thenReturn(allergenEntity);
        DeleteAllergenRequest request = DeleteAllergenRequest.builder()
                .name("allergen")
                .build();
        DeleteAllergenResponse er = DeleteAllergenResponse.builder()
                .response("Allergen: allergen has been deleted!")
                .build();
        DeleteAllergenResponse ar = allergenService.deleteAllergen(request);
        assertEquals(er,ar);
    }

    @Test
    void addAllergenToProduct() {
        when(allergenRepoMock.findByNameIsLike("allergen")).thenReturn(allergenEntity);
        when(productRepoMock.findByNameIsLike("product")).thenReturn(productEntity);
        when(productAllergenRepoMock.findByProductAndAllergenId(1,1)).thenReturn(null);
        AddAllergenToProductRequest request = AddAllergenToProductRequest.builder()
                .product("product")
                .allergen("allergen")
                .build();
        AddAllergenToProductResponse er = AddAllergenToProductResponse.builder()
                .response("Allergen: allergen has been added to product: product!")
                .build();
        AddAllergenToProductResponse ar = allergenService.addAllergenToProduct(request);
        assertEquals(er,ar);
    }

    @Test
    void deleteProductAllergen() {
        when(allergenRepoMock.findByNameIsLike("allergen")).thenReturn(allergenEntity);
        when(productRepoMock.findByNameIsLike("product")).thenReturn(productEntity);
        when(productAllergenRepoMock.findByProductAndAllergenId(1,1)).thenReturn(productAllergenEntity);
        DeleteProductAllergenRequest request = DeleteProductAllergenRequest.builder()
                .allergen("allergen")
                .product("product")
                .build();
        DeleteProductAllergenResponse er = DeleteProductAllergenResponse.builder()
                .response("Allergen: allergen was deleted from product: product")
                .build();
        DeleteProductAllergenResponse ar = allergenService.deleteProductAllergen(request);
        assertEquals(ar,er);
    }

    @Test
    void getAllProductAllergen() {
        when(productRepoMock.findByNameIsLike("product")).thenReturn(productEntity);
        when(productAllergenRepoMock.findAllAllergensByProductIdIsLike(1)).thenReturn(List.of("allergen"));
        GetAllProductAllergensRequest request = GetAllProductAllergensRequest.builder()
                .product("product")
                .build();
        GetAllProductAllergensResponse er = GetAllProductAllergensResponse.builder()
                .allergens(List.of("allergen"))
                .build();
        GetAllProductAllergensResponse ar = allergenService.getAllProductAllergen(request);
        assertEquals(er,ar);
    }
}