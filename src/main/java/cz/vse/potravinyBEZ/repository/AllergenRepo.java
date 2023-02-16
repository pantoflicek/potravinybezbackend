package cz.vse.potravinyBEZ.repository;

import cz.vse.potravinyBEZ.repository.entity.AllergenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergenRepo extends JpaRepository<AllergenEntity, Long> {
    AllergenEntity findByNameIsLike(String name);
}
