package cz.vse.potravinyBEZ.service.impl;

import cz.vse.potravinyBEZ.domain.producer.*;
import cz.vse.potravinyBEZ.repository.ProducerRepo;
import cz.vse.potravinyBEZ.repository.entity.ProducerEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProducerServiceImplTest {

    @Mock
    private ProducerRepo producerRepoMock;

    @InjectMocks
    private ProducerServiceImpl producerService;

    ProducerEntity producerEntity = ProducerEntity.builder()
            .id(1)
            .name("producer")
            .build();

    Producer producer = Producer.builder()
            .id(1)
            .name("producer")
            .build();

    @Test
    void createProducer() {
        when(producerRepoMock.findByNameIsLike("producer")).thenReturn(null);
        CreateProducerRequest request = CreateProducerRequest.builder()
                .name("producer")
                .build();
        CreateProducerResponse er = CreateProducerResponse.builder()
                .response("Producer: producer has been created!")
                .build();
        CreateProducerResponse ar = producerService.createProducer(request);
        assertEquals(er,ar);
    }

    @Test
    void getAllProducers() {
        when(producerRepoMock.findAll()).thenReturn(List.of(producerEntity));
        GetAllProducersResponse er = GetAllProducersResponse.builder()
                .producers(List.of(producer))
                .build();
        GetAllProducersResponse ar = producerService.getAllProducers();
        assertEquals(er,ar);
    }

    @Test
    void deleteProducerByName() {
        when(producerRepoMock.findByNameIsLike("producer")).thenReturn(producerEntity);
        DeleteProducerRequest request = DeleteProducerRequest.builder()
                .name("producer")
                .build();
        DeleteProducerResponse er = DeleteProducerResponse.builder()
                .response("Producer: producer has been deleted!")
                .build();
        DeleteProducerResponse ar = producerService.deleteProducerByName(request);
        assertEquals(ar,er);
    }
}