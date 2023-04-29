package cz.vse.potravinyBEZ.service.impl;

import cz.vse.potravinyBEZ.domain.offer.*;
import cz.vse.potravinyBEZ.domain.product.Product;
import cz.vse.potravinyBEZ.domain.vendor.Vendor;
import cz.vse.potravinyBEZ.repository.OfferRepo;
import cz.vse.potravinyBEZ.repository.ProductRepo;
import cz.vse.potravinyBEZ.repository.VendorRepo;
import cz.vse.potravinyBEZ.repository.entity.OfferEntity;
import cz.vse.potravinyBEZ.repository.entity.ProductEntity;
import cz.vse.potravinyBEZ.repository.entity.VendorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

//Mockito
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OfferServiceImplTest {

    @Mock
    private OfferRepo offerRepoMock;

    @Mock
    private VendorRepo vendorRepoMock;
    @Mock
    private ProductRepo productRepoMock;
    @InjectMocks
    private OfferServiceImpl offerService;

    private final VendorEntity vendorEntity = VendorEntity.builder()
            .id(1)
            .name("vendor")
            .build();

    private final Vendor vendor = Vendor.builder()
            .id(1)
            .name("vendor")
            .build();

    private final ProductEntity productEntity = ProductEntity.builder()
            .id(1)
            .name("product")
            .build();

    private final Product product = Product.builder()
            .id(1)
            .name("product")
            .build();

    private final OfferEntity offerEntity = OfferEntity.builder()
            .id(1)
            .price(1)
            .vendor(vendorEntity)
            .product(productEntity)
            .dateAdded(new Date())
            .build();

    private final Offer offer = Offer.builder()
            .id(1)
            .price(1)
            .vendor(vendor)
            .product(product)
            .dateAdded(new Date())
            .build();

    @Test
    void createOffer() {
        Date date = new Date();
        when(vendorRepoMock.findByNameIsLike("vendor")).thenReturn(vendorEntity);
        when(productRepoMock.findByNameIsLike("product")).thenReturn(productEntity);
        CreateOfferRequest newOffer = CreateOfferRequest.builder()
                .vendor("vendor")
                .product("product")
                .dateAdded(date)
                .price(50)
                .build();
        CreateOfferResponse aR = offerService.createOffer(newOffer);
        CreateOfferResponse eR = CreateOfferResponse.builder()
                .response("Product: product has new offer from vendor: vendor with price: 50.0")
                .build();
        assertEquals(aR, eR);
    }

    @Test
    void createOfferNullVendor(){
        Date date = new Date();
        when(vendorRepoMock.findByNameIsLike("vendor")).thenReturn(null);
        when(productRepoMock.findByNameIsLike("product")).thenReturn(productEntity);
        CreateOfferRequest newOffer = CreateOfferRequest.builder()
                .vendor("vendor")
                .product("product")
                .dateAdded(date)
                .price(50)
                .build();
        CreateOfferResponse aR = offerService.createOffer(newOffer);
        CreateOfferResponse eR = CreateOfferResponse.builder()
                .response("Specified vendor does not exists!")
                .build();
        assertEquals(aR, eR);
    }

    @Test
    void createOfferNullProduct(){
        Date date = new Date();
        when(vendorRepoMock.findByNameIsLike("vendor")).thenReturn(vendorEntity);
        when(productRepoMock.findByNameIsLike("product")).thenReturn(null);
        CreateOfferRequest newOffer = CreateOfferRequest.builder()
                .vendor("vendor")
                .product("product")
                .dateAdded(date)
                .price(50)
                .build();
        CreateOfferResponse aR = offerService.createOffer(newOffer);
        CreateOfferResponse eR = CreateOfferResponse.builder()
                .response("Specified product does not exists!")
                .build();
        assertEquals(aR, eR);
    }

    @Test
    void createOfferAlreadyExists() {
        Date date = new Date();
        when(vendorRepoMock.findByNameIsLike("vendor")).thenReturn(vendorEntity);
        when(productRepoMock.findByNameIsLike("product")).thenReturn(productEntity);
        when(offerRepoMock.findByProductIdAndVendorId(productEntity.getId(), vendorEntity.getId())).thenReturn(offerEntity);
        CreateOfferRequest newOffer = CreateOfferRequest.builder()
                .vendor("vendor")
                .product("product")
                .dateAdded(date)
                .price(50)
                .build();
        CreateOfferResponse aR = offerService.createOffer(newOffer);
        CreateOfferResponse eR = CreateOfferResponse.builder()
                .response("Specified product already has an offer from this vendor!")
                .build();
        assertEquals(aR, eR);
    }

    @Test
    void deleteOffer() {
        when(vendorRepoMock.findByNameIsLike("vendor")).thenReturn(vendorEntity);
        when(productRepoMock.findByNameIsLike("product")).thenReturn(productEntity);
        when(offerRepoMock.findByProductIdAndVendorId(productEntity.getId(), vendorEntity.getId())).thenReturn(offerEntity);
        DeleteOfferRequest deleteOffer = DeleteOfferRequest.builder()
                .vendor("vendor")
                .product("product")
                .build();
        DeleteOfferResponse aR = offerService.deleteOffer(deleteOffer);
        DeleteOfferResponse eR = DeleteOfferResponse.builder()
                .response("Offer from vendor: vendor has been deleted from product: product")
                .build();
        assertEquals(aR, eR);
    }

    @Test
    void deleteOfferNullProduct() {
        when(vendorRepoMock.findByNameIsLike("vendor")).thenReturn(vendorEntity);
        when(productRepoMock.findByNameIsLike("product")).thenReturn(null);
        DeleteOfferRequest deleteOffer = DeleteOfferRequest.builder()
                .vendor("vendor")
                .product("product")
                .build();
        DeleteOfferResponse aR = offerService.deleteOffer(deleteOffer);
        DeleteOfferResponse eR = DeleteOfferResponse.builder()
                .response("Specified product does not exists!")
                .build();
        assertEquals(aR, eR);
    }

    @Test
    void deleteOfferNullVendor() {
        when(vendorRepoMock.findByNameIsLike("vendor")).thenReturn(null);
        when(productRepoMock.findByNameIsLike("product")).thenReturn(productEntity);
        DeleteOfferRequest deleteOffer = DeleteOfferRequest.builder()
                .vendor("vendor")
                .product("product")
                .build();
        DeleteOfferResponse aR = offerService.deleteOffer(deleteOffer);
        DeleteOfferResponse eR = DeleteOfferResponse.builder()
                .response("Specified vendor does not exists!")
                .build();
        assertEquals(aR, eR);
    }

    @Test
    void deleteOfferNullOffer() {
        when(vendorRepoMock.findByNameIsLike("vendor")).thenReturn(vendorEntity);
        when(productRepoMock.findByNameIsLike("product")).thenReturn(productEntity);
        when(offerRepoMock.findByProductIdAndVendorId(productEntity.getId(), vendorEntity.getId())).thenReturn(null);
        DeleteOfferRequest deleteOffer = DeleteOfferRequest.builder()
                .vendor("vendor")
                .product("product")
                .build();
        DeleteOfferResponse aR = offerService.deleteOffer(deleteOffer);
        DeleteOfferResponse eR = DeleteOfferResponse.builder()
                .response("Product does not have the specified offer!")
                .build();
        assertEquals(aR, eR);
    }

    @Test
    void getAllProductOffersNullProduct() {
        when(productRepoMock.findByNameIsLike(productEntity.getName())).thenReturn(null);
        GetAllProductOffersRequest request = GetAllProductOffersRequest.builder()
                .product("product")
                .build();
        GetAllProductOffersResponse aR = offerService.getAllProductOffers(request);
        assertNull(aR);
    }

    @Test
    void getProductLowestPrice() {
        when(productRepoMock.findByNameIsLike(productEntity.getName())).thenReturn(productEntity);
        when(offerRepoMock.findLowestByProductId(productEntity.getId())).thenReturn(offerEntity.getPrice());
        GetProductLowestPriceRequest request = GetProductLowestPriceRequest.builder()
                .product("product")
                .build();
        GetProductLowestPriceResponse aR = offerService.getProductLowestPrice(request);
        GetProductLowestPriceResponse eR = GetProductLowestPriceResponse.builder()
                .lowestOffer(1)
                .build();
        assertEquals(aR, eR);
    }

    @Test
    void getProductHighestPrice() {
        when(productRepoMock.findByNameIsLike(productEntity.getName())).thenReturn(productEntity);
        when(offerRepoMock.findHighestByProductId(productEntity.getId())).thenReturn(offerEntity.getPrice());
        GetProductHighestPriceRequest request = GetProductHighestPriceRequest.builder()
                .product("product")
                .build();
        GetProductHighestPriceResponse aR = offerService.getProductHighestPrice(request);
        GetProductHighestPriceResponse eR = GetProductHighestPriceResponse.builder()
                .highestOffer(1)
                .build();
        assertEquals(eR, aR);
    }
}