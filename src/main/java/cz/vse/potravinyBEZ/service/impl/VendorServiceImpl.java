package cz.vse.potravinyBEZ.service.impl;

import cz.vse.potravinyBEZ.domain.converter.EntityToVendorConverter;
import cz.vse.potravinyBEZ.domain.vendor.*;
import cz.vse.potravinyBEZ.repository.VendorRepo;
import cz.vse.potravinyBEZ.repository.entity.VendorEntity;
import cz.vse.potravinyBEZ.service.VendorService;

//Lombok
import lombok.AllArgsConstructor;

//Spring
import org.springframework.stereotype.Service;

//Java
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class VendorServiceImpl implements  VendorService{
    private final VendorRepo vendorRepo;

    @Override
    public CreateVendorResponse createVendor(CreateVendorRequest vendor) {
        VendorEntity newVendor = VendorEntity.builder()
                .name(vendor.getName())
                .website(vendor.getWebsite())
                .logoPath(vendor.getLogoPath())
                .address(vendor.getAddress())
                .mail(vendor.getMail())
                .phone(vendor.getPhone())
                .contactPerson(vendor.getContactPerson())
                .openingHours(vendor.getOpeningHours())
                .additionalInfo(vendor.getAdditionalInfo())
                .build();
        VendorEntity isExisting = vendorRepo.findByNameIsLike(newVendor.getName());
        if (Objects.isNull(isExisting)){
            vendorRepo.save(newVendor);
            return CreateVendorResponse.builder()
                    .response("Vendor: " + newVendor.getName() + " has been created!")
                    .build();
        } else {
            return CreateVendorResponse.builder()
                    .response("Vendor already exists")
                    .build();
        }
    }

    @Override
    public GetAllVendorsResponse getAllVendors() {
        List<VendorEntity> allVendorEntities = vendorRepo.findAll();
        List<Vendor> allVendors = allVendorEntities.stream()
                .map(EntityToVendorConverter::convert)
                .toList();
        return GetAllVendorsResponse.builder()
                .vendors(allVendors)
                .build();
    }

    @Override
    public DeleteVendorResponse deleteVendor(DeleteVendorRequest vendor) {
        VendorEntity vendorToDelete = vendorRepo.findByNameIsLike(vendor.getName());
        if (Objects.isNull(vendorToDelete)){
            return DeleteVendorResponse.builder()
                    .response("Specified vendor does not exists!")
                    .build();
        } else {
            vendorRepo.delete(vendorToDelete);
            return DeleteVendorResponse.builder()
                    .response("Vendor: " + vendorToDelete.getName() + " has been delete!")
                    .build();
        }
    }

    @Override
    public GetVendorByIdResponse getVendorById(GetVendorByIdRequest id) {
        VendorEntity vendorEntity = vendorRepo.findByIdIsLike(id.getId());
        if (Objects.isNull(vendorEntity)){
            return GetVendorByIdResponse.builder()
                    .vendor(null)
                    .build();
        } else {
            Vendor vendor = EntityToVendorConverter.convert(vendorEntity);
            return GetVendorByIdResponse.builder()
                    .vendor(vendor)
                    .build();
        }
    }
}
