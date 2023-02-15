package cz.vse.potravinyBEZ.service.impl;

import cz.vse.potravinyBEZ.domain.converter.EntityToUserConverter;
import cz.vse.potravinyBEZ.domain.user.*;
import cz.vse.potravinyBEZ.repository.UserRepository;
import cz.vse.potravinyBEZ.repository.entity.UserEntity;
import cz.vse.potravinyBEZ.service.UserService;

//Lombok
import lombok.AllArgsConstructor;

//Spring
import org.springframework.stereotype.Service;

//Java
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public CreateUserResponse createUser(CreateUserRequest user) {
        UserEntity newUser = UserEntity.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
        userRepository.save(newUser);
        return CreateUserResponse.builder()
                .id(newUser.getId())
                .build();
    }

    @Override
    public GetAllUsersResponse getAllUsers() {
        List<User> users = userRepository.findAll().stream()
                .map(EntityToUserConverter::convert)
                .toList();
        return GetAllUsersResponse.builder()
                .users(users)
                .build();
    }

    @Override
    public DeleteUserResponse deleteUserById(int id) {
        UserEntity userToDelete = userRepository.findById(id);
        assert userToDelete != null;
        userRepository.delete(userToDelete);
        return DeleteUserResponse.builder()
                .name(userToDelete.getUsername())
                .build();
    }
}
