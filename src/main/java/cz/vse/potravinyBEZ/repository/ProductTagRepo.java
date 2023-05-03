package cz.vse.potravinyBEZ.repository;

import cz.vse.potravinyBEZ.repository.entity.ProductTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductTagRepo extends JpaRepository<ProductTagEntity, Long> {
    @Query("SELECT u.tag.name FROM ProductTagEntity u WHERE u.product.id = ?1")
    List<String> findAllTagsByProductIdIsLike(Integer id);

    @Query("SELECT u FROM ProductTagEntity u WHERE u.product.id = ?1 AND u.tag.id = ?2")
    ProductTagEntity findByProductAndTagId(Integer productId, Integer tagId);
}
