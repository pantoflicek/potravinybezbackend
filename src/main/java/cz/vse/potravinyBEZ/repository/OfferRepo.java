package cz.vse.potravinyBEZ.repository;

import cz.vse.potravinyBEZ.repository.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfferRepo extends JpaRepository<OfferEntity, Long> {
    //Find all offers
    @Query("SELECT u FROM OfferEntity u WHERE u.product.id = ?1")
    List<OfferEntity> findByProductId(Integer id);
    //Find the lowest offer
    @Query("SELECT u.price FROM OfferEntity u WHERE u.product.id = ?1 ORDER BY u.price ASC FETCH FIRST 1 ROW ONLY")
    Integer findLowestByProductId(Integer id);
    @Query("SELECT u FROM OfferEntity u WHERE u.product.id = ?1 AND u.vendor.id = ?2")
    OfferEntity findByProductIdAndVendorId(Integer productId, Integer vendorId);
}
