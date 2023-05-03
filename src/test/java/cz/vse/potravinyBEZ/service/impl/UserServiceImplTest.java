package cz.vse.potravinyBEZ.service.impl;

import cz.vse.potravinyBEZ.domain.user.*;
import cz.vse.potravinyBEZ.repository.RoleRepo;
import cz.vse.potravinyBEZ.repository.UserRepo;
import cz.vse.potravinyBEZ.repository.entity.RoleEntity;
import cz.vse.potravinyBEZ.repository.entity.UserEntity;
import cz.vse.potravinyBEZ.service.RoleService;
import jakarta.validation.constraints.Null;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepo userRepoMock;
    @Mock
    private PasswordEncoder passwordEncoderMock;
    @Mock
    private RoleRepo roleRepoMock;
    @Mock
    private RoleService roleServiceMock;

    @InjectMocks
    private UserServiceImpl userService;

    UserEntity userEntity = UserEntity.builder()
            .id(1)
            .password("password")
            .username("username")
            .email("email@email.com")
            .build();

    User user = User.builder()
            .id(1)
            .password("password")
            .username("username")
            .email("email@email.com")
            .build();

    RoleEntity roleEntity = RoleEntity.builder()
            .id(1)
            .name("USER")
            .build();

    /*@Test
    void createUser() {
        when(passwordEncoderMock.encode("password")).thenReturn("password");
        when(userRepoMock.findByUsernameIsLike(userEntity.getUsername())).thenReturn(null);
        when(userRepoMock.findByEmailIsLike(userEntity.getEmail())).thenReturn(null);
        when(roleRepoMock.findByNameIsLike("USER")).thenReturn(roleEntity);
        when(userRepoMock.findByUsernameIsLike("username")).thenReturn(userEntity);
        CreateUserRequest request = CreateUserRequest.builder()
                .username("username")
                .password("password")
                .email("email@email.com")
                .build();
        CreateUserResponse eR = CreateUserResponse.builder()
                .response("User: username has been created with id: 1")
                .build();
        CreateUserResponse aR = userService.createUser(request);
        assertEquals(eR, aR);
    }*/

    @Test
    void getAllUsers() {
        when(userRepoMock.findAll()).thenReturn(List.of(userEntity));
        GetAllUsersResponse eR = GetAllUsersResponse.builder()
                .users(List.of(user))
                .build();
        GetAllUsersResponse aR = userService.getAllUsers();
        assertEquals(eR, aR);
    }

    @Test
    void deleteUserByName() {
        when(userRepoMock.findByUsernameIsLike(userEntity.getUsername())).thenReturn(userEntity);
        DeleteUserRequest request = DeleteUserRequest.builder()
                .username("username")
                .build();
        DeleteUserResponse eR = DeleteUserResponse.builder()
                .response("User: username has been deleted!")
                .build();
        DeleteUserResponse aR = userService.deleteUserByName(request);
        assertEquals(eR, aR);
    }
}