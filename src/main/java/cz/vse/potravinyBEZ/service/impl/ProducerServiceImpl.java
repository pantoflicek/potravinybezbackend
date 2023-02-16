package cz.vse.potravinyBEZ.service.impl;

import cz.vse.potravinyBEZ.domain.converter.EntityToProducerConverter;
import cz.vse.potravinyBEZ.domain.producer.*;
import cz.vse.potravinyBEZ.repository.ProducerRepo;
import cz.vse.potravinyBEZ.repository.entity.ProducerEntity;
import cz.vse.potravinyBEZ.service.ProducerService;

//Lombok
import lombok.AllArgsConstructor;

//Spring
import org.springframework.stereotype.Service;

//Java
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ProducerServiceImpl implements ProducerService {
    private final ProducerRepo producerRepo;

    @Override
    public CreateProducerResponse createProducer(CreateProducerRequest producer) {
        ProducerEntity newProducer = ProducerEntity.builder()
                .name(producer.getName())
                .build();
        ProducerEntity isExisting = producerRepo.findByNameIsLike(newProducer.getName());
        if (Objects.isNull(isExisting)){
            producerRepo.save(newProducer);
            return CreateProducerResponse.builder()
                    .response("Producer: " + newProducer.getName() + " has been created!")
                    .build();
        } else {
            return CreateProducerResponse.builder()
                    .response("Producer already exists!")
                    .build();
        }
    }

    @Override
    public GetAllProducersResponse getAllProducers() {
        List<ProducerEntity> allProducersEntity = producerRepo.findAll();
        List<Producer> allProducers = allProducersEntity.stream()
                .map(EntityToProducerConverter::convert)
                .toList();
        return GetAllProducersResponse.builder()
                .producers(allProducers)
                .build();
    }

    @Override
    public DeleteProducerResponse deleteProducerByName(DeleteProducerRequest producer) {
        ProducerEntity producerToDelete = producerRepo.findByNameIsLike(producer.getName());
        if (Objects.isNull(producerToDelete)){
            return DeleteProducerResponse.builder()
                    .response("Specified producer does not exists!")
                    .build();
        } else {
            return DeleteProducerResponse.builder()
                    .response("Producer: " + producerToDelete.getName() + " has been deleted!")
                    .build();
        }
    }
}
