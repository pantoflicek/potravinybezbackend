package cz.vse.potravinyBEZ.service.impl;


import cz.vse.potravinyBEZ.domain.converter.EntityToProductConverter;
import cz.vse.potravinyBEZ.domain.product.*;
import cz.vse.potravinyBEZ.repository.ProducerRepo;
import cz.vse.potravinyBEZ.repository.ProductRepo;
import cz.vse.potravinyBEZ.repository.entity.ProducerEntity;
import cz.vse.potravinyBEZ.repository.entity.ProductEntity;
import cz.vse.potravinyBEZ.service.ProductService;

//Lombok
import lombok.AllArgsConstructor;

//Spring
import org.springframework.stereotype.Service;

//Java
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final ProducerRepo producerRepo;

    @Override
    public CreateProductResponse createProduct(CreateProductRequest product) {
        ProducerEntity addingProducer = producerRepo.findByNameIsLike(product.getProducer());
        if (Objects.isNull(addingProducer)){
            ProducerEntity newProducer = ProducerEntity.builder()
                    .name(product.getProducer())
                    .build();
            producerRepo.save(newProducer);
            addingProducer = producerRepo.findByNameIsLike(product.getProducer());
        }
        ProductEntity newProduct = ProductEntity.builder()
                .name(product.getName())
                .imagePath(product.getImagePath())
                .descriptionShort(product.getDescriptionShort())
                .descriptionLong(product.getDescriptionLong())
                .weight(product.getWeight())
                .ingredients(product.getIngredients())
                .producer(addingProducer)
                .energy(product.getEnergy())
                .fat(product.getFat())
                .carbohydrate(product.getCarbohydrate())
                .sugars(product.getSugars())
                .fibre(product.getFibre())
                .protein(product.getProtein())
                .salt(product.getSalt())
                .build();
        ProductEntity isExisting = productRepo.findByNameIsLike(newProduct.getName());
        if (Objects.isNull(isExisting)){
            productRepo.save(newProduct);
             return CreateProductResponse.builder()
                    .response("Product: " + newProduct.getName() + " has been created!")
                    .build();
        } else {
            return CreateProductResponse.builder()
                    .response("Product already exists!")
                    .build();
        }
    }


    @Override
    public GetAllProductsResponse getAllProducts() {
        List<ProductEntity> allProductEntities = productRepo.findAll();
        List<Product> allProducts = allProductEntities.stream()
                .map(EntityToProductConverter::convert)
                .toList();
        return GetAllProductsResponse.builder()
                .products(allProducts)
                .build();
    }

    @Override
    public DeleteProductResponse deleteProduct(DeleteProductRequest product) {
        ProductEntity productToDelete = productRepo.findByNameIsLike(product.getName());
        if (Objects.isNull(productToDelete)){
            return DeleteProductResponse.builder()
                    .response("Specified product does not exists!")
                    .build();
        } else {
            productRepo.delete(productToDelete);
            return DeleteProductResponse.builder()
                    .response("Product: " + productToDelete.getName() + " has been deleted!")
                    .build();
        }
    }

    @Override
    public GetProductByProductIdResponse getProductByProductId(GetProductByProductIdRequest id) {
        ProductEntity productEntity = productRepo.findByIdIsLike(id.getId());
        if (Objects.isNull(productEntity)){
            return GetProductByProductIdResponse.builder()
                    .product(null)
                    .build();
        } else {
            Product product = EntityToProductConverter.convert(productEntity);
            return GetProductByProductIdResponse.builder()
                    .product(product)
                    .build();
        }
    }

    @Override
    public FindByNameResponse findByName(FindByNameRequest name) {
        List<ProductEntity> productEntities = productRepo.findByNameIsLIke(name.getName());
        List<Product> foundProducts = productEntities.stream()
                .map(EntityToProductConverter::convert)
                .toList();
        return FindByNameResponse.builder()
                .products(foundProducts)
                .build();
    }

    @Override
    public FindByNameSpecificResponse findByNameSpecific(FindByNameSpecificRequest name) {
        ProductEntity productEntity = productRepo.findByNameSpecificProduct(name.getName());
        Product foundProduct = EntityToProductConverter.convert(productEntity);
        return FindByNameSpecificResponse.builder()
                .product(foundProduct)
                .build();
    }

    @Override
    public FindByAllergenResponse findByAllergen(FindByAllergenRequest allergenId) {
        List<ProductEntity> productEntities = productRepo.findByAllergen(allergenId.getAllergenId());
        List<Product> foundProducts = productEntities.stream()
                .map(EntityToProductConverter::convert)
                .toList();
        return FindByAllergenResponse.builder()
                .products(foundProducts)
                .build();
    }

    @Override
    public FindLastFiveProductsResponse findLastFiveProducts() {
        List<ProductEntity> productEntities = productRepo.findLastFive();
        List<Product> foundProducts = productEntities.stream()
                .map(EntityToProductConverter::convert)
                .toList();
        return FindLastFiveProductsResponse.builder()
                .products(foundProducts)
                .build();
    }
}
