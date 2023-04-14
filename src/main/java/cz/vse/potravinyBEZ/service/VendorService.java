package cz.vse.potravinyBEZ.service;

import cz.vse.potravinyBEZ.domain.vendor.*;

public interface VendorService {
    CreateVendorResponse createVendor(CreateVendorRequest vendor);
    GetAllVendorsResponse getAllVendors();
    DeleteVendorResponse deleteVendor(DeleteVendorRequest vendor);
    GetVendorByIdResponse getVendorById(GetVendorByIdRequest id);
}
