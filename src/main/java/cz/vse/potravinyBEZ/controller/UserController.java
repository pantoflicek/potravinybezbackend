package cz.vse.potravinyBEZ.controller;

//Spring
import cz.vse.potravinyBEZ.domain.user.CreateUserRequest;
import cz.vse.potravinyBEZ.domain.user.CreateUserResponse;
import cz.vse.potravinyBEZ.domain.user.DeleteUserResponse;
import cz.vse.potravinyBEZ.domain.user.GetAllUsersResponse;
import cz.vse.potravinyBEZ.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

//Persistence
import jakarta.validation.Valid;

//Lombok
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "http://localhost:3000/")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public CreateUserResponse createUser(@Valid @NonNull @RequestBody CreateUserRequest createUserRequest){
        return userService.createUser(createUserRequest);
    }

    @GetMapping
    public ResponseEntity<GetAllUsersResponse> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping(path = "{id}")
    public DeleteUserResponse deleteUserById(@PathVariable("id") int id){
        return userService.deleteUserById(id);
    }
}
