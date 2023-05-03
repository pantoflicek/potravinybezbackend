package cz.vse.potravinyBEZ.controller;

//Persistence
import cz.vse.potravinyBEZ.configuration.security.isAuth.IsAuthenticated;
import cz.vse.potravinyBEZ.domain.vendor.*;
import cz.vse.potravinyBEZ.service.VendorService;
import jakarta.annotation.security.RolesAllowed;
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
    @IsAuthenticated
    public CreateVendorResponse createVendor(@Valid @NonNull @RequestBody CreateVendorRequest createVendorRequest){
        return vendorService.createVendor(createVendorRequest);
    }

    @DeleteMapping
    @Transactional
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public DeleteVendorResponse deleteVendor(@Valid @NonNull @RequestBody DeleteVendorRequest deleteVendorRequest){
        return vendorService.deleteVendor(deleteVendorRequest);
    }

    @GetMapping
    public GetAllVendorsResponse getAllVendors(){
        return vendorService.getAllVendors();
    }

    @PutMapping
    public GetVendorByIdResponse getVendorById(@Valid @NonNull @RequestBody GetVendorByIdRequest getVendorByIdRequest){
        return vendorService.getVendorById(getVendorByIdRequest);
    }
}
