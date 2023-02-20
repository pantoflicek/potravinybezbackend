package cz.vse.potravinyBEZ.service;

import cz.vse.potravinyBEZ.domain.allergen.*;

public interface AllergenService {
    CreateAllergenResponse createAllergen(CreateAllergenRequest allergen);
    GetAllAllergenResponse getAllAllergens();
    DeleteAllergenResponse deleteAllergen(DeleteAllergenRequest allergen);
    AddAllergenToProductResponse addAllergenToProduct(AddAllergenToProductRequest request);
    DeleteProductAllergenResponse deleteProductAllergen(DeleteProductAllergenRequest request);
    GetAllProductAllergensResponse getAllProductAllergen(GetAllProductAllergensRequest request);
}
