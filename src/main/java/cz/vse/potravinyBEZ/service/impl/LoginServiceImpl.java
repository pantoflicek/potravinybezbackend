package cz.vse.potravinyBEZ.service.impl;

import cz.vse.potravinyBEZ.domain.login.LoginRequest;
import cz.vse.potravinyBEZ.domain.login.LoginResponse;
import cz.vse.potravinyBEZ.domain.security.AccessToken;
import cz.vse.potravinyBEZ.repository.UserRepo;
import cz.vse.potravinyBEZ.repository.UserRoleRepo;
import cz.vse.potravinyBEZ.repository.entity.UserEntity;
import cz.vse.potravinyBEZ.service.AccessTokenEncoder;
import cz.vse.potravinyBEZ.service.LoginService;
import cz.vse.potravinyBEZ.service.exception.loginService.InvalidCredentialsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final UserRoleRepo userRoleRepo;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity user = userRepo.findByUsernameIsLike(loginRequest.getUsername());
        if (user == null) {
            throw new InvalidCredentialsException();
        }
        if (!matchesPassword(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException();
        }
        String accessToken = generateAccessToken(user);
        return LoginResponse.builder().accessToken(accessToken).build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(UserEntity user){
        int userId = user.getId();
        List<String> roles = userRoleRepo.findAllRolesByUserIdIsLike(user.getId());
        return accessTokenEncoder.encode(
                AccessToken.builder()
                        .subject(user.getUsername())
                        .roles(roles)
                        .userId(userId)
                        .build()
        );
    }

}
