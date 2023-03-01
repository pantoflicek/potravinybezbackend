package cz.vse.potravinyBEZ.controller;

//Persistence
import cz.vse.potravinyBEZ.domain.offer.*;
import cz.vse.potravinyBEZ.service.OfferService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

//Lombok
import lombok.AllArgsConstructor;

//Spring
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("offers")
@CrossOrigin(origins = "http://localhost:3000/")
@AllArgsConstructor
public class OfferController {
    private final OfferService offerService;

    @PostMapping
    public CreateOfferResponse createOffer(@Valid @NonNull @RequestBody CreateOfferRequest createOfferRequest){
        return offerService.createOffer(createOfferRequest);
    }

    @DeleteMapping
    public DeleteOfferResponse deleteOffer(@Valid @NonNull @RequestBody DeleteOfferRequest deleteOffer){
        return offerService.deleteOffer(deleteOffer);
    }

    @PutMapping
    @Transactional
    public GetAllProductOffersResponse getAllProductOffers(@Valid @NonNull @RequestBody GetAllProductOffersRequest request){
        return offerService.getAllProductOffers(request);
    }

    @PutMapping("/lowestPrice")
    public GetProductLowestPriceResponse getProductLowestPrice(@Valid @NonNull @RequestBody GetProductLowestPriceRequest request){
        return offerService.getProductLowestPrice(request);
    }
}
