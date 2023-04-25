package cz.vse.potravinyBEZ.service.impl;

import cz.vse.potravinyBEZ.domain.offer.CreateOfferRequest;
import cz.vse.potravinyBEZ.domain.offer.CreateOfferResponse;
import cz.vse.potravinyBEZ.repository.OfferRepo;
import cz.vse.potravinyBEZ.repository.ProductRepo;
import cz.vse.potravinyBEZ.repository.VendorRepo;
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

    private final ProductEntity productEntity = ProductEntity.builder()
            .id(1)
            .name("product")
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
                .response("Product: product has new offer from vendor: vendor with price: 50")
                .build();
        assertEquals(aR, eR);
    }

    @Test
    void deleteOffer() {
    }

    @Test
    void getAllProductOffers() {
    }

    @Test
    void getProductLowestPrice() {
    }

    @Test
    void getProductHighestPrice() {
    }
}