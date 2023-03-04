package cz.vse.potravinyBEZ.controller;

import cz.vse.potravinyBEZ.domain.role.*;
import cz.vse.potravinyBEZ.service.RoleService;

//Persistence
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

//Lombok
import lombok.AllArgsConstructor;

//Spring
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("roles")
@CrossOrigin(origins = "http://localhost:3000/")
@AllArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public CreateRoleResponse createRole(@Valid @NonNull @RequestBody CreateRoleRequest createRoleRequest){
        return roleService.createRole(createRoleRequest);
    }

    @DeleteMapping
    @Transactional
    public DeleteRoleResponse deleteRole(@Valid @NonNull @RequestBody DeleteRoleRequest deleteRoleRequest){
        return roleService.deleteRole(deleteRoleRequest);
    }

    @GetMapping
    public GetAllRolesResponse getAllRoles(){
        return roleService.getAllRoles();
    }

    @PutMapping("/addRole")
    public AddRoleToUserResponse addRoleToUser(@Valid @NonNull @RequestBody AddRoleToUserRequest addRoleToUserRequest){
        return roleService.addRoleToUser(addRoleToUserRequest);
    }

    @PostMapping("/userRoles")
    public GetAllUsersRoleResponse getAllUsersRole(@Valid @NonNull @RequestBody GetAllUsersRolesRequest getAllUsersRolesRequest){
        return roleService.getAllUsersRole(getAllUsersRolesRequest);
    }

    @DeleteMapping("/userRoleDelete")
    public DeleteUserRoleResponse deleteUserRole(@Valid @NonNull @RequestBody DeleteUserRoleRequest deleteUserRoleRequest){
        return roleService.deleteUserRole(deleteUserRoleRequest);
    }
}
