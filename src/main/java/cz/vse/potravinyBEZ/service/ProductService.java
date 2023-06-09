package cz.vse.potravinyBEZ.service;

import cz.vse.potravinyBEZ.domain.product.*;

public interface ProductService {
    CreateProductResponse createProduct(CreateProductRequest product);
    GetAllProductsResponse getAllProducts();
    DeleteProductResponse deleteProduct(DeleteProductRequest product);
    GetProductByProductIdResponse getProductByProductId(GetProductByProductIdRequest id);
    FindByNameResponse findByName(FindByNameRequest name);
    FindByNameSpecificResponse findByNameSpecific(FindByNameSpecificRequest name);
    FindByAllergenResponse findByAllergen(FindByAllergenRequest allergenId);
    FindLastFiveProductsResponse findLastFiveProducts();
}