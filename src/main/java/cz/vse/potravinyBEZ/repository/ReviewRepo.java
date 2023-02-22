package cz.vse.potravinyBEZ.repository;

import cz.vse.potravinyBEZ.repository.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepo extends JpaRepository<ReviewEntity, Long> {
    @Query("SELECT u FROM ReviewEntity u WHERE u.product.id = ?1")
    List<ReviewEntity> findByProductId(Integer id);
    @Query("SELECT u FROM ReviewEntity u WHERE u.product.id = ?1 AND u.user.id = ?2")
    ReviewEntity findByProductIdAndUserId(Integer productId, Integer userId);

}
