package cz.vse.potravinyBEZ.service.impl;

import cz.vse.potravinyBEZ.domain.converter.EntityToUserConverter;
import cz.vse.potravinyBEZ.domain.role.AddRoleToUserRequest;
import cz.vse.potravinyBEZ.domain.role.CreateRoleRequest;
import cz.vse.potravinyBEZ.domain.user.*;
import cz.vse.potravinyBEZ.repository.RoleRepo;
import cz.vse.potravinyBEZ.repository.UserRepo;
import cz.vse.potravinyBEZ.repository.entity.RoleEntity;
import cz.vse.potravinyBEZ.repository.entity.UserEntity;
import cz.vse.potravinyBEZ.service.RoleService;
import cz.vse.potravinyBEZ.service.UserService;

//Lombok
import lombok.AllArgsConstructor;

//Spring
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//Java
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;
    private final RoleService roleService;

    @Override
    public CreateUserResponse createUser(CreateUserRequest user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        UserEntity newUser = UserEntity.builder()
                .username(user.getUsername())
                .password(encodedPassword)
                .email(user.getEmail())
                .build();
        UserEntity isExisting = userRepo.findByUsernameIsLike(newUser.getUsername());
        UserEntity isExistingByEmail = userRepo.findByEmailIsLike(newUser.getEmail());
        if (Objects.isNull(isExisting)){
            if (Objects.isNull(isExistingByEmail)){
                userRepo.save(newUser);
                RoleEntity isRoleExisting = roleRepo.findByNameIsLike("USER");
                if (Objects.isNull(isRoleExisting)){
                    CreateRoleRequest request = CreateRoleRequest.builder()
                            .name("USER")
                            .build();
                    roleService.createRole(request);
                }
                AddRoleToUserRequest addRoleToUserRequest = AddRoleToUserRequest.builder()
                        .role("USER")
                        .username(newUser.getUsername())
                        .build();
                roleService.addRoleToUser(addRoleToUserRequest);
                return CreateUserResponse.builder()
                        .response("User: " + newUser.getUsername() + " has been created with id: " + userRepo.findByUsernameIsLike(newUser.getUsername()).getId())
                        .build();
            } else {
                return CreateUserResponse.builder()
                        .response("Email is already taken!")
                        .build();
            }
        } else {
            return CreateUserResponse.builder()
                    .response("Username is already taken!")
                    .build();
        }
    }

    @Override
    public GetAllUsersResponse getAllUsers() {
        List<User> users = userRepo.findAll().stream()
                .map(EntityToUserConverter::convert)
                .toList();
        return GetAllUsersResponse.builder()
                .users(users)
                .build();
    }

    @Override
    public DeleteUserResponse deleteUserByName(DeleteUserRequest user) {
        UserEntity userToDelete = userRepo.findByUsernameIsLike(user.getUsername());
        if (userToDelete == null){
            return DeleteUserResponse.builder()
                    .response("Specified user does not exists!")
                    .build();
        } else {
            userRepo.delete(userToDelete);
            return DeleteUserResponse.builder()
                    .response("User: " + userToDelete.getUsername() + " has been deleted!")
                    .build();
        }
    }
}
