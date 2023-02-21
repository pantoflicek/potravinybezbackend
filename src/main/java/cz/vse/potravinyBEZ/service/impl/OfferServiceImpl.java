package cz.vse.potravinyBEZ.service.impl;

import cz.vse.potravinyBEZ.domain.converter.EntityToProductConverter;
import cz.vse.potravinyBEZ.domain.converter.EntityToVendorConverter;
import cz.vse.potravinyBEZ.domain.offer.*;
import cz.vse.potravinyBEZ.repository.OfferRepo;
import cz.vse.potravinyBEZ.repository.ProductRepo;
import cz.vse.potravinyBEZ.repository.VendorRepo;
import cz.vse.potravinyBEZ.repository.entity.OfferEntity;
import cz.vse.potravinyBEZ.repository.entity.ProductEntity;
import cz.vse.potravinyBEZ.repository.entity.VendorEntity;
import cz.vse.potravinyBEZ.service.OfferService;

//Lombok
import lombok.AllArgsConstructor;

//Spring
import org.springframework.stereotype.Service;

//Java
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final VendorRepo vendorRepo;
    private final ProductRepo productRepo;
    private final OfferRepo offerRepo;
    @Override
    public CreateOfferResponse createOffer(CreateOfferRequest offer) {
        VendorEntity addingVendor = vendorRepo.findByNameIsLike(offer.getVendor());
        ProductEntity addingProduct = productRepo.findByNameIsLike(offer.getProduct());
        OfferEntity newOffer = OfferEntity.builder()
                .price(offer.getPrice())
                .product(addingProduct)
                .vendor(addingVendor)
                .build();
        if (addingVendor == null){
            return CreateOfferResponse.builder()
                    .response("Specified vendor does not exists!")
                    .build();
        } else if (addingProduct == null) {
            return CreateOfferResponse.builder()
                    .response("Specified product does not exists!")
                    .build();
        } else {
            OfferEntity isExisting = offerRepo.findByProductIdAndVendorId(addingProduct.getId(), addingVendor.getId());
            if (Objects.isNull(isExisting)){
                offerRepo.save(newOffer);
                return CreateOfferResponse.builder()
                        .response("Product: " + addingProduct.getName() + " has new offer from vendor: " + addingVendor.getName() + " with price: " + newOffer.getPrice())
                        .build();
            } else {
                return CreateOfferResponse.builder()
                        .response("Specified product already has an offer from this vendor!")
                        .build();
            }
        }
    }

    @Override
    public DeleteOfferResponse deleteOffer(DeleteOfferRequest offer) {
        ProductEntity deletingProduct = productRepo.findByNameIsLike(offer.getProduct());
        VendorEntity deletingVendor = vendorRepo.findByNameIsLike(offer.getVendor());
        if (deletingProduct == null){
            return DeleteOfferResponse.builder()
                    .response("Specified product does not exists!")
                    .build();
        } else if (deletingVendor == null) {
            return DeleteOfferResponse.builder()
                    .response("Specified vendor does not exists!")
                    .build();
        } else {
            OfferEntity offerToDelete = offerRepo.findByProductIdAndVendorId(deletingProduct.getId(), deletingVendor.getId());
            if (Objects.isNull(offerToDelete)){
                return DeleteOfferResponse.builder()
                        .response("Product does not have the specified offer!")
                        .build();
            } else {
                offerRepo.delete(offerToDelete);
                return DeleteOfferResponse.builder()
                        .response("Offer form vendor: " + deletingVendor.getName() + " has been deleted from product: " + deletingProduct.getName())
                        .build();
            }
        }
    }

    @Override
    public GetAllProductOffersResponse getAllProductOffers(GetAllProductOffersRequest request) {
        ProductEntity product = productRepo.findByNameIsLike(request.getProduct());
        if (product == null){
            System.out.println("No product found");
            return null;
        } else {
            List<OfferEntity> offerEntities = offerRepo.findByProductId(product.getId());
            List<Offer> offers = offerEntities.stream()
                    .map(offerEntity -> new Offer(offerEntity.getId(), offerEntity.getPrice(), EntityToProductConverter.convert(offerEntity.getProduct()), EntityToVendorConverter.convert(offerEntity.getVendor())))
                    .toList();
            return GetAllProductOffersResponse.builder()
                    .offers(offers)
                    .build();
        }
    }
}
