package cz.vse.potravinyBEZ.service.impl;

import cz.vse.potravinyBEZ.domain.converter.EntityToRoleConverter;
import cz.vse.potravinyBEZ.domain.role.*;
import cz.vse.potravinyBEZ.repository.RoleRepo;
import cz.vse.potravinyBEZ.repository.UserRepo;
import cz.vse.potravinyBEZ.repository.UserRoleRepo;
import cz.vse.potravinyBEZ.repository.entity.RoleEntity;
import cz.vse.potravinyBEZ.repository.entity.UserEntity;
import cz.vse.potravinyBEZ.repository.entity.UserRoleEntity;
import cz.vse.potravinyBEZ.service.RoleService;

//Lombok
import lombok.AllArgsConstructor;

//Spring
import org.springframework.stereotype.Service;

//Java
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;
    private final UserRepo userRepo;
    private final UserRoleRepo userRoleRepo;

    @Override
    public CreateRoleResponse createRole(CreateRoleRequest role) {
        RoleEntity newRole = RoleEntity.builder()
                .name(role.getName())
                .build();
        RoleEntity isExisting = roleRepo.findByNameIsLike(newRole.getName());
        if (Objects.isNull(isExisting)) {
            roleRepo.save(newRole);
            return CreateRoleResponse.builder()
                    .response("Role: " + newRole.getName() + " has been created!")
                    .build();
        } else {
            return CreateRoleResponse.builder()
                    .response("Role already exists!")
                    .build();
        }
    }

    @Override
    public DeleteRoleResponse deleteRole(DeleteRoleRequest role) {
        RoleEntity roleToDelete = roleRepo.findByNameIsLike(role.getName());
        if (Objects.isNull(roleToDelete)){
            return DeleteRoleResponse.builder()
                    .response("Specified role does not exists!")
                    .build();
        } else {
            roleRepo.delete(roleToDelete);
            return DeleteRoleResponse.builder()
                    .response("Role: " + roleToDelete.getName() + " has been deleted!")
                    .build();
        }
    }

    @Override
    public GetAllRolesResponse getAllRoles() {
        List<RoleEntity> allRolesEntity = roleRepo.findAll();
        List<Role> allRoles = allRolesEntity.stream()
                .map(EntityToRoleConverter::convert)
                .toList();
        return GetAllRolesResponse.builder()
                .roles(allRoles)
                .build();
    }

    @Override
    public AddRoleToUserResponse addRoleToUser(AddRoleToUserRequest request) {
        RoleEntity addingRole = roleRepo.findByNameIsLike(request.getRole());
        UserEntity addingUser = userRepo.findByUsernameIsLike(request.getUsername());
        UserRoleEntity userRoleEntity = UserRoleEntity.builder()
                .user(addingUser)
                .role(addingRole)
                .build();
        if (addingUser == null){
            return AddRoleToUserResponse.builder()
                    .response("Specified user does not exists!")
                    .build();
        }
        else if (addingRole == null){
            return AddRoleToUserResponse.builder()
                    .response("Specified roel does not exists!")
                    .build();
        }
        else {
            UserRoleEntity isExisting = userRoleRepo.findByUserAndRole(addingUser.getId(), addingRole.getId());
            if (Objects.isNull(isExisting)){
                userRoleRepo.save(userRoleEntity);
                return AddRoleToUserResponse.builder()
                        .response("Role: " + addingRole.getName() + " has been added to user: " + addingUser.getUsername() + "!")
                        .build();
            } else {
                return AddRoleToUserResponse.builder()
                        .response("Specified user already has this role!")
                        .build();
            }
        }
    }

    @Override
    public DeleteUserRoleResponse deleteUserRole(DeleteUserRoleRequest request) {
        RoleEntity deletingRole = roleRepo.findByNameIsLike(request.getRole());
        UserEntity deletingUser = userRepo.findByUsernameIsLike(request.getUsername());
        if (deletingUser == null){
            return DeleteUserRoleResponse.builder()
                    .response("Specified user does not exists!")
                    .build();
        }
        else if (deletingRole == null){
            return DeleteUserRoleResponse.builder()
                    .response("Specified role does not exists!")
                    .build();
        } else {
            UserRoleEntity entityToDelete = userRoleRepo.findByUserAndRole(deletingUser.getId(), deletingRole.getId());
            if (Objects.isNull(entityToDelete)){
                return DeleteUserRoleResponse
                        .builder()
                        .response("User does not have the specified role!")
                        .build();
            } else {
                userRoleRepo.delete(entityToDelete);
                return DeleteUserRoleResponse
                        .builder()
                        .response("Role: " + deletingRole.getName() + " was deleted from user: " + deletingUser.getUsername())
                        .build();
            }
        }
    }

    @Override
    public GetAllUsersRoleResponse getAllUsersRole(GetAllUsersRolesRequest request) {
        UserEntity user = userRepo.findByUsernameIsLike(request.getUsername());
        List<String> list = userRoleRepo.findAllRolesByUserIdIsLike(user.getId());
        return GetAllUsersRoleResponse.builder().roles(list).build();
    }
}
