package cz.vse.potravinyBEZ.service;

import cz.vse.potravinyBEZ.domain.producer.*;

public interface ProducerService {
    CreateProducerResponse createProducer(CreateProducerRequest producer);
    GetAllProducersResponse getAllProducers();
    DeleteProducerResponse deleteProducerByName(DeleteProducerRequest producer);
}
