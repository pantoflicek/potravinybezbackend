package cz.vse.potravinyBEZ.service;

import cz.vse.potravinyBEZ.domain.role.*;

public interface RoleService {
    CreateRoleResponse createRole(CreateRoleRequest role);
    DeleteRoleResponse deleteRole(DeleteRoleRequest role);
    GetAllRolesResponse getAllRoles();
    AddRoleToUserResponse addRoleToUser(AddRoleToUserRequest request);
    DeleteUserRoleResponse deleteUserRole(DeleteUserRoleRequest request);
    GetAllUsersRoleResponse getAllUsersRole(GetAllUsersRolesRequest request);
}
