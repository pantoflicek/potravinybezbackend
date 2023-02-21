package cz.vse.potravinyBEZ.repository;

import cz.vse.potravinyBEZ.repository.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfferRepo extends JpaRepository<OfferEntity,Long> {
    @Query("SELECT u FROM OfferEntity u WHERE u.product.id = ?1")
    List<OfferEntity> findByProductId(Integer id);
}
