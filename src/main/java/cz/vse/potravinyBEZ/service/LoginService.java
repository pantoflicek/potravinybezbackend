package cz.vse.potravinyBEZ.service;


import cz.vse.potravinyBEZ.domain.login.*;


public interface LoginService {
    LoginResponse login(LoginRequest loginRequest);
}
