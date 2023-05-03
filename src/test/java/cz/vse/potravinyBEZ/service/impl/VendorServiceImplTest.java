package cz.vse.potravinyBEZ.service.impl;

import cz.vse.potravinyBEZ.domain.vendor.*;
import cz.vse.potravinyBEZ.repository.VendorRepo;
import cz.vse.potravinyBEZ.repository.entity.VendorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VendorServiceImplTest {

    @Mock
    private VendorRepo vendorRepoMock;

    @InjectMocks
    private VendorServiceImpl vendorService;

    private final VendorEntity vendorEntity = VendorEntity.builder()
            .name("vendor")
            .id(1)
            .build();

    private final Vendor vendor = Vendor.builder()
            .name("vendor")
            .id(1)
            .build();

    @Test
    void createVendor() {
        when(vendorRepoMock.findByNameIsLike("vendor")).thenReturn(null);
        CreateVendorRequest request = CreateVendorRequest.builder()
                .name("vendor")
                .build();
        CreateVendorResponse eR = CreateVendorResponse.builder()
                .response("Vendor: vendor has been created!")
                .build();
        CreateVendorResponse aR = vendorService.createVendor(request);
        assertEquals(eR, aR);
    }

    @Test
    void getAllVendors() {
        when(vendorRepoMock.findAll()).thenReturn(List.of(vendorEntity));
        GetAllVendorsResponse eR = GetAllVendorsResponse.builder()
                .vendors(List.of(vendor))
                .build();
        GetAllVendorsResponse aR = vendorService.getAllVendors();
        assertEquals(eR, aR);
    }

    @Test
    void deleteVendor() {
        when(vendorRepoMock.findByNameIsLike("vendor")).thenReturn(vendorEntity);
        DeleteVendorRequest request = DeleteVendorRequest.builder()
                .name("vendor")
                .build();
        DeleteVendorResponse eR = DeleteVendorResponse.builder()
                .response("Vendor: vendor has been deleted!")
                .build();
        DeleteVendorResponse aR = vendorService.deleteVendor(request);
        assertEquals(eR, aR);
    }

    @Test
    void getVendorById() {
        when(vendorRepoMock.findByIdIsLike(1)).thenReturn(vendorEntity);
        GetVendorByIdRequest request = GetVendorByIdRequest.builder()
                .id(1)
                .build();
        GetVendorByIdResponse eR = GetVendorByIdResponse.builder()
                .vendor(vendor)
                .build();
        GetVendorByIdResponse aR = vendorService.getVendorById(request);
        assertEquals(eR, aR);
    }
}