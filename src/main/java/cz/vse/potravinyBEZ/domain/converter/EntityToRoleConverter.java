package cz.vse.potravinyBEZ.domain.converter;


import cz.vse.potravinyBEZ.domain.role.Role;
import cz.vse.potravinyBEZ.repository.entity.RoleEntity;

public final class EntityToRoleConverter {
    public EntityToRoleConverter(){
    }
    public static Role convert(RoleEntity roleEntity){
        return Role.builder()
                .id(roleEntity.getId())
                .name(roleEntity.getName())
                .build();
    }
}
