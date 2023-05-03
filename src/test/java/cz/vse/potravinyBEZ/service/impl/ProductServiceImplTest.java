package cz.vse.potravinyBEZ.service.impl;

import cz.vse.potravinyBEZ.domain.producer.Producer;
import cz.vse.potravinyBEZ.domain.product.*;
import cz.vse.potravinyBEZ.repository.ProducerRepo;
import cz.vse.potravinyBEZ.repository.ProductRepo;
import cz.vse.potravinyBEZ.repository.entity.ProducerEntity;
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
class ProductServiceImplTest {
    @Mock
    private ProductRepo productRepoMock;
    @Mock
    private ProducerRepo producerRepoMock;

    @InjectMocks
    private ProductServiceImpl productService;

    ProducerEntity producerEntity = ProducerEntity.builder()
            .id(1)
            .name("producer")
            .build();

    Producer producer = Producer.builder()
            .id(1)
            .name("producer")
            .build();

    ProductEntity productEntity = ProductEntity.builder()
            .id(1)
            .name("product")
            .producer(producerEntity)
            .build();

    Product product = Product.builder()
            .id(1)
            .name("product")
            .producer(producer)
            .build();

    @Test
    void createProduct() {
        when(producerRepoMock.findByNameIsLike("producer")).thenReturn(producerEntity);
        when(productRepoMock.findByNameIsLike("product")).thenReturn(null);
        CreateProductRequest request = CreateProductRequest.builder()
                .name("product")
                .producer("producer")
                .build();
        CreateProductResponse er = CreateProductResponse.builder()
                .response("Product: product has been created!")
                .build();
        CreateProductResponse ar = productService.createProduct(request);
        assertEquals(er,ar);
    }

    @Test
    void getAllProducts() {
        when(productRepoMock.findAll()).thenReturn(List.of(productEntity));
        GetAllProductsResponse er = GetAllProductsResponse.builder()
                .products(List.of(product))
                .build();
        GetAllProductsResponse ar = productService.getAllProducts();
        assertEquals(er,ar);
    }

    @Test
    void deleteProduct() {
        when(productRepoMock.findByNameIsLike("product")).thenReturn(productEntity);
        DeleteProductRequest request = DeleteProductRequest.builder()
                .name("product")
                .build();
        DeleteProductResponse er = DeleteProductResponse.builder()
                .response("Product: product has been deleted!")
                .build();
        DeleteProductResponse ar = productService.deleteProduct(request);
        assertEquals(er,ar);
    }

    @Test
    void getProductByProductId() {
        when(productRepoMock.findByIdIsLike(1)).thenReturn(productEntity);
        GetProductByProductIdRequest request = GetProductByProductIdRequest.builder()
                .id(1)
                .build();
        GetProductByProductIdResponse er = GetProductByProductIdResponse.builder()
                .product(product)
                .build();
        GetProductByProductIdResponse ar = productService.getProductByProductId(request);
        assertEquals(er,ar);
    }

    @Test
    void findByName() {
        when(productRepoMock.findByNameIsLIke("product")).thenReturn(List.of(productEntity));
        FindByNameRequest request = FindByNameRequest.builder()
                .name("product")
                .build();
        FindByNameResponse er = FindByNameResponse.builder()
                .products(List.of(product))
                .build();
        FindByNameResponse ar = productService.findByName(request);
        assertEquals(er,ar);
    }

    @Test
    void findByNameSpecific() {
        when(productRepoMock.findByNameSpecificProduct("product")).thenReturn(productEntity);
        FindByNameSpecificRequest request = FindByNameSpecificRequest.builder()
                .name("product")
                .build();
        FindByNameSpecificResponse er = FindByNameSpecificResponse.builder()
                .product(product)
                .build();
        FindByNameSpecificResponse ar = productService.findByNameSpecific(request);
        assertEquals(er,ar);
    }

    @Test
    void findByAllergen() {
        when(productRepoMock.findByAllergen(1)).thenReturn(List.of(productEntity));
        FindByAllergenRequest request = FindByAllergenRequest.builder()
                .allergenId(1)
                .build();
        FindByAllergenResponse er = FindByAllergenResponse.builder()
                .products(List.of(product))
                .build();
        FindByAllergenResponse ar = productService.findByAllergen(request);
        assertEquals(er,ar);
    }

    @Test
    void findLastFiveProducts() {
        when(productRepoMock.findLastFive()).thenReturn(List.of(productEntity));
        FindLastFiveProductsResponse er = FindLastFiveProductsResponse.builder()
                .products(List.of(product))
                .build();
        FindLastFiveProductsResponse ar = productService.findLastFiveProducts();
        assertEquals(er,ar);
    }
}