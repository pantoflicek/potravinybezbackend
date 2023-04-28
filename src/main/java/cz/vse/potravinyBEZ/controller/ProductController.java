package cz.vse.potravinyBEZ.controller;

//Persistence
import cz.vse.potravinyBEZ.domain.product.*;
import cz.vse.potravinyBEZ.service.ProductService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

//Lombok
import lombok.AllArgsConstructor;

//Spring
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
@CrossOrigin(origins = "http://localhost:3000/")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public CreateProductResponse createProduct(@Valid @NonNull @RequestBody CreateProductRequest createProductRequest){
        return productService.createProduct(createProductRequest);
    }

    @DeleteMapping
    @Transactional
    public DeleteProductResponse deleteAllergen(@Valid @NonNull @RequestBody DeleteProductRequest deleteProductRequest){
        return productService.deleteProduct(deleteProductRequest);
    }

    @GetMapping
    public GetAllProductsResponse getAllProducts(){
        return productService.getAllProducts();
    }

    @PutMapping
    public FindByNameResponse findByName(@Valid @NonNull @RequestBody FindByNameRequest request){
        return productService.findByName(request);
    }

    @PutMapping("/specificName")
    public FindByNameSpecificResponse findByNameSpecific(@Valid @NonNull @RequestBody FindByNameSpecificRequest request){
        return productService.findByNameSpecific(request);
    }

    @PutMapping("/byAllergen")
    public FindByAllergenResponse findByAllergen(@Valid @NonNull @RequestBody FindByAllergenRequest request){
        return productService.findByAllergen(request);
    }

    @GetMapping("/lastFive")
    public FindLastFiveProductsResponse findLastFiveProducts(){
        return productService.findLastFiveProducts();
    }

    @PutMapping("/product")
    public GetProductByProductIdResponse getProductByProductId(@Valid @NonNull @RequestBody GetProductByProductIdRequest getProductByProductIdRequest){
        return productService.getProductByProductId(getProductByProductIdRequest);
    }

}
