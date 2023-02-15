package cz.vse.potravinyBEZ.service;

import cz.vse.potravinyBEZ.domain.user.*;

public interface UserService {
    CreateUserResponse createUser(CreateUserRequest user);
    GetAllUsersResponse getAllUsers();
    DeleteUserResponse deleteUserById(int id);
}
