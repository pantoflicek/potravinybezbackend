package cz.vse.potravinyBEZ.service;

import cz.vse.potravinyBEZ.domain.security.AccessToken;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
