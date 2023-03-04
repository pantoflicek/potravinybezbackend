package cz.vse.potravinyBEZ.controller;

//Persistence
import cz.vse.potravinyBEZ.domain.vendor.*;
import cz.vse.potravinyBEZ.service.VendorService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

//Lombok
import lombok.AllArgsConstructor;

//Spring
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("vendors")
@CrossOrigin(origins = "http://localhost:3000/")
@AllArgsConstructor
public class VendorController {
    private final VendorService vendorService;

    @PostMapping
    public CreateVendorResponse createVendor(@Valid @NonNull @RequestBody CreateVendorRequest createVendorRequest){
        return vendorService.createVendor(createVendorRequest);
    }

    @DeleteMapping
    @Transactional
    public DeleteVendorResponse deleteVendor(@Valid @NonNull @RequestBody DeleteVendorRequest deleteVendorRequest){
        return vendorService.deleteVendor(deleteVendorRequest);
    }

    @GetMapping
    public GetAllVendorsResponse getAllVendors(){
        return vendorService.getAllVendors();
    }
}
