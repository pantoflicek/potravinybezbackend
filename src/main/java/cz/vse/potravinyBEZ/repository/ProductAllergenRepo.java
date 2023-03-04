package cz.vse.potravinyBEZ.repository;

import cz.vse.potravinyBEZ.repository.entity.ProductAllergenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductAllergenRepo extends JpaRepository<ProductAllergenEntity, Long> {
    @Query("SELECT u.allergen.name FROM ProductAllergenEntity u WHERE u.product.id = ?1")
    List<String> findAllAllergensByProductIdIsLike(Integer id);
    @Query("SELECT u FROM ProductAllergenEntity u WHERE u.product.id = ?1 AND u.allergen.id = ?2")
    ProductAllergenEntity findByProductAndAllergenId(Integer productId, Integer allergenId);
}
