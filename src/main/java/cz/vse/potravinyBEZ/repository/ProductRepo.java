package cz.vse.potravinyBEZ.repository;

import cz.vse.potravinyBEZ.repository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<ProductEntity, Long> {
    ProductEntity findByIdIsLike(int id);
    ProductEntity findByNameIsLike(String name);
    @Query("SELECT u FROM ProductEntity u WHERE u.name LIKE %?1%")
    List<ProductEntity> findByNameIsLIke(String name);
}
