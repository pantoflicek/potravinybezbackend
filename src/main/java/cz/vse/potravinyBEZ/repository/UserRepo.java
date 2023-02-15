package cz.vse.potravinyBEZ.repository;

import cz.vse.potravinyBEZ.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsernameIsLike(String username);
    UserEntity findById(int id);
}
