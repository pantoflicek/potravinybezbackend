package cz.vse.potravinyBEZ.repository;

import cz.vse.potravinyBEZ.repository.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleRepo extends JpaRepository<UserRoleEntity,Long> {
    @Query("SELECT u.role.name FROM UserRoleEntity u WHERE u.user.id = ?1")
    List<String> findAllRolesByUserIdIsLike(Integer id);
    @Query("SELECT u FROM UserRoleEntity u WHERE u.user.id = ?1 AND u.role.id = ?2")
    UserRoleEntity findByUserAndRole(Integer userId, Integer roleId);
}
