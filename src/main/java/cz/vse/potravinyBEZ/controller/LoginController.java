package cz.vse.potravinyBEZ.controller;

import cz.vse.potravinyBEZ.domain.login.LoginRequest;
import cz.vse.potravinyBEZ.domain.login.LoginResponse;
import cz.vse.potravinyBEZ.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000/")
@RequiredArgsConstructor
public class LoginController {
    public final LoginService loginService;
    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = loginService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}
