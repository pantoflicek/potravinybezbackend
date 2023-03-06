package cz.vse.potravinyBEZ.service;

import cz.vse.potravinyBEZ.domain.security.AccessToken;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
