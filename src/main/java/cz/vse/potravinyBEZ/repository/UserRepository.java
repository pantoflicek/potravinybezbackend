package cz.vse.potravinyBEZ.repository;

import cz.vse.potravinyBEZ.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsernameIsLike(String username);
}
