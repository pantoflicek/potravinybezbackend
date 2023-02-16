package cz.vse.potravinyBEZ.controller;

//Persistence
import cz.vse.potravinyBEZ.domain.allergen.*;
import cz.vse.potravinyBEZ.service.AllergenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

//Lombok
import lombok.AllArgsConstructor;

//Spring
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("allergens")
@CrossOrigin(origins = "http://localhost:3000/")
@AllArgsConstructor
public class AllergenController {
    private final AllergenService allergenService;

    @PostMapping
    public CreateAllergenResponse createAllergen(@Valid @NonNull @RequestBody CreateAllergenRequest createAllergenRequest){
        return allergenService.createAllergen(createAllergenRequest);
    }

    @DeleteMapping
    @Transactional
    public DeleteAllergenResponse deleteAllergen(@Valid @NonNull @RequestBody DeleteAllergenRequest deleteAllergenRequest){
        return allergenService.deleteAllergen(deleteAllergenRequest);
    }

    @GetMapping
    public GetAllAllergenResponse getAllAllergen(){
        return allergenService.getAllAllergens();
    }
}
