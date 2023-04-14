package cz.vse.potravinyBEZ.repository;

import cz.vse.potravinyBEZ.repository.entity.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepo extends JpaRepository<VendorEntity, Long> {
    VendorEntity findByNameIsLike(String name);
    VendorEntity findByIdIsLike(int id);
}
