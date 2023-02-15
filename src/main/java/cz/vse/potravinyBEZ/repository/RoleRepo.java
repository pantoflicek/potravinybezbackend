package cz.vse.potravinyBEZ.repository;

import cz.vse.potravinyBEZ.repository.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByNameIsLike(String name);
}
