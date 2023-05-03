package cz.vse.potravinyBEZ.service.impl;

import cz.vse.potravinyBEZ.domain.role.*;
import cz.vse.potravinyBEZ.repository.RoleRepo;
import cz.vse.potravinyBEZ.repository.UserRepo;
import cz.vse.potravinyBEZ.repository.UserRoleRepo;
import cz.vse.potravinyBEZ.repository.entity.RoleEntity;
import cz.vse.potravinyBEZ.repository.entity.UserEntity;
import cz.vse.potravinyBEZ.repository.entity.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @Mock
    private RoleRepo roleRepoMock;
    @Mock
    private UserRepo userRepoMock;
    @Mock
    private UserRoleRepo userRoleRepo;

    @InjectMocks
    private RoleServiceImpl roleService;

    RoleEntity roleEntity = RoleEntity.builder()
            .id(1)
            .name("role")
            .build();

    Role role = Role.builder()
            .id(1)
            .name("role")
            .build();

    UserEntity userEntity = UserEntity.builder()
            .id(1)
            .username("user")
            .build();

    UserRoleEntity userRoleEntity = UserRoleEntity.builder()
            .id(1)
            .user(userEntity)
            .role(roleEntity)
            .build();

    @Test
    void createRole() {
        when(roleRepoMock.findByNameIsLike("role")).thenReturn(null);
        CreateRoleRequest request = CreateRoleRequest.builder()
                .name("role")
                .build();
        CreateRoleResponse er = CreateRoleResponse.builder()
                .response("Role: role has been created!")
                .build();
        CreateRoleResponse ar = roleService.createRole(request);
        assertEquals(er,ar);
    }

    @Test
    void deleteRole() {
        when(roleRepoMock.findByNameIsLike("role")).thenReturn(roleEntity);
        DeleteRoleRequest request = DeleteRoleRequest.builder()
                .name("role")
                .build();
        DeleteRoleResponse er = DeleteRoleResponse.builder()
                .response("Role: role has been deleted!")
                .build();
        DeleteRoleResponse ar = roleService.deleteRole(request);
        assertEquals(er,ar);
    }

    @Test
    void getAllRoles() {
        when(roleRepoMock.findAll()).thenReturn(List.of(roleEntity));
        GetAllRolesResponse er = GetAllRolesResponse.builder()
                .roles(List.of(role))
                .build();
        GetAllRolesResponse ar = roleService.getAllRoles();
        assertEquals(er,ar);
    }

    @Test
    void addRoleToUser() {
        when(roleRepoMock.findByNameIsLike("role")).thenReturn(roleEntity);
        when(userRepoMock.findByUsernameIsLike("user")).thenReturn(userEntity);
        when(userRoleRepo.findByUserAndRole(1,1)).thenReturn(null);
        AddRoleToUserRequest request = AddRoleToUserRequest.builder()
                .username("user")
                .role("role")
                .build();
        AddRoleToUserResponse er = AddRoleToUserResponse.builder()
                .response("Role: role has been added to user: user!")
                .build();
        AddRoleToUserResponse ar = roleService.addRoleToUser(request);
        assertEquals(er,ar);
    }

    @Test
    void deleteUserRole() {
        when(roleRepoMock.findByNameIsLike("role")).thenReturn(roleEntity);
        when(userRepoMock.findByUsernameIsLike("user")).thenReturn(userEntity);
        when(userRoleRepo.findByUserAndRole(1,1)).thenReturn(userRoleEntity);
        DeleteUserRoleRequest request = DeleteUserRoleRequest.builder()
                .username("user")
                .role("role")
                .build();
        DeleteUserRoleResponse er = DeleteUserRoleResponse.builder()
                .response("Role: role was deleted from user: user")
                .build();
        DeleteUserRoleResponse ar = roleService.deleteUserRole(request);
        assertEquals(er,ar);
    }

    @Test
    void getAllUsersRole() {
        when(userRepoMock.findByUsernameIsLike("user")).thenReturn(userEntity);
        when(userRoleRepo.findAllRolesByUserIdIsLike(1)).thenReturn(List.of("role"));
        GetAllUsersRolesRequest request = GetAllUsersRolesRequest.builder()
                .username("user")
                .build();
        GetAllUsersRoleResponse er = GetAllUsersRoleResponse.builder()
                .roles(List.of("role"))
                .build();
        GetAllUsersRoleResponse ar = roleService.getAllUsersRole(request);
        assertEquals(er,ar);
    }
}