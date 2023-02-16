package cz.vse.potravinyBEZ.service.impl;

import cz.vse.potravinyBEZ.domain.converter.EntityToUserConverter;
import cz.vse.potravinyBEZ.domain.user.*;
import cz.vse.potravinyBEZ.repository.UserRepo;
import cz.vse.potravinyBEZ.repository.entity.UserEntity;
import cz.vse.potravinyBEZ.service.UserService;

//Lombok
import lombok.AllArgsConstructor;

//Spring
import org.springframework.stereotype.Service;

//Java
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public CreateUserResponse createUser(CreateUserRequest user) {
        UserEntity newUser = UserEntity.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
        UserEntity isExisting = userRepo.findByUsernameIsLike(newUser.getUsername());
        UserEntity isExistingByEmail = userRepo.findByEmailIsLike(newUser.getEmail());
        if (Objects.isNull(isExisting)){
            if (Objects.isNull(isExistingByEmail)){
                userRepo.save(newUser);
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
    public DeleteUserResponse deleteUserById(DeleteUserRequest user) {
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
