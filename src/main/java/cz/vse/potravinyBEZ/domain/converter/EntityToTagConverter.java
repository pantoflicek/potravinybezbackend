package cz.vse.potravinyBEZ.domain.converter;

import cz.vse.potravinyBEZ.domain.tag.Tag;
import cz.vse.potravinyBEZ.repository.entity.TagEntity;

public final class EntityToTagConverter {
    public EntityToTagConverter(){
    }
    public static Tag convert(TagEntity tagEntity){
        return Tag.builder()
                .id(tagEntity.getId())
                .name(tagEntity.getName())
                .build();
    }
}
