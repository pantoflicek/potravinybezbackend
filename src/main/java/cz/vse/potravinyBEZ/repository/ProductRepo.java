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
    @Query("SELECT u FROM ProductEntity u WHERE u.name LIKE ?1")
    ProductEntity findByNameSpecificProduct(String name);
    @Query("select p from ProductEntity p join ProductAllergenEntity a on p.id = a.product.id where a.allergen.id = ?1")
    List<ProductEntity> findByAllergen(Integer allergenId);
    @Query("select p from ProductEntity p order by p.id desc fetch first 5 row only")
    List<ProductEntity> findLastFive();
}
