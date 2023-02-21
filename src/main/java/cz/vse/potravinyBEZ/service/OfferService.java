package cz.vse.potravinyBEZ.service;

import cz.vse.potravinyBEZ.domain.offer.*;

public interface OfferService {
    CreateOfferResponse createOffer(CreateOfferRequest offer);
    DeleteOfferResponse deleteOffer(DeleteOfferRequest offer);
    GetAllProductOffersResponse getAllProductOffers(GetAllProductOffersRequest request);
}
