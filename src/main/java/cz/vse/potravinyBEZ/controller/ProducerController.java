package cz.vse.potravinyBEZ.controller;

import cz.vse.potravinyBEZ.configuration.security.isAuth.IsAuthenticated;
import cz.vse.potravinyBEZ.domain.producer.*;
import cz.vse.potravinyBEZ.service.ProducerService;

//Persistence
import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

//Lombok
import lombok.AllArgsConstructor;

//Spring
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("producers")
@CrossOrigin(origins = "http://localhost:3000/")
@AllArgsConstructor
public class ProducerController {
    private final ProducerService producerService;

    @PostMapping
    @IsAuthenticated
    public CreateProducerResponse createProducer(@Valid @NonNull @RequestBody CreateProducerRequest createProducerRequest){
        return producerService.createProducer(createProducerRequest);
    }

    @DeleteMapping
    @Transactional
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public DeleteProducerResponse deleteProducer(@Valid @NonNull @RequestBody DeleteProducerRequest deleteProducerRequest){
        return producerService.deleteProducerByName(deleteProducerRequest);
    }

    @GetMapping
    public GetAllProducersResponse getAllProducers(){
        return producerService.getAllProducers();
    }
}
