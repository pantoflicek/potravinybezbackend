package cz.vse.potravinyBEZ.domain.converter;

import cz.vse.potravinyBEZ.domain.producer.Producer;
import cz.vse.potravinyBEZ.repository.entity.ProducerEntity;

public final class EntityToProducerConverter {
    public EntityToProducerConverter(){
    }
    public static Producer convert(ProducerEntity producerEntity){
        return Producer.builder()
                .id(producerEntity.getId())
                .name(producerEntity.getName())
                .build();
    }
}
