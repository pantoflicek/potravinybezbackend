package cz.vse.potravinyBEZ.domain.converter;

import cz.vse.potravinyBEZ.domain.producer.Producer;
import cz.vse.potravinyBEZ.repository.entity.ProducerEntity;

public class EntityToProducerConverter {
    public EntityToProducerConverter(){
    }
    public static Producer convert(ProducerEntity roleEntity){
        return Producer.builder()
                .id(roleEntity.getId())
                .name(roleEntity.getName())
                .build();
    }
}
