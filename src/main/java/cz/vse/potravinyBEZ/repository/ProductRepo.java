package cz.vse.potravinyBEZ.repository;

import cz.vse.potravinyBEZ.repository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<ProductEntity, Long> {
    ProductEntity findByNameIsLike(String name);
}