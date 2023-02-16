package cz.vse.potravinyBEZ.domain.converter;

import cz.vse.potravinyBEZ.domain.vendor.Vendor;
import cz.vse.potravinyBEZ.repository.entity.VendorEntity;

public final class EntityToVendorConverter {
    public EntityToVendorConverter(){
    }
    public static Vendor convert(VendorEntity vendorEntity){
        return Vendor.builder()
                .id(vendorEntity.getId())
                .name(vendorEntity.getName())
                .logoPath(vendorEntity.getLogoPath())
                .website(vendorEntity.getWebsite())
                .build();
    }
}
