package cz.vse.potravinyBEZ.domain.converter;

import cz.vse.potravinyBEZ.domain.user.User;
import cz.vse.potravinyBEZ.repository.entity.UserEntity;

public final class EntityToUserConverter {
    public EntityToUserConverter(){
    }
    public static User convert(UserEntity userEntity){
        return User.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .build();
    }
}

