package cz.vse.potravinyBEZ.domain.converter;

import cz.vse.potravinyBEZ.domain.producer.Producer;
import cz.vse.potravinyBEZ.domain.product.Product;
import cz.vse.potravinyBEZ.repository.entity.ProducerEntity;
import cz.vse.potravinyBEZ.repository.entity.ProductEntity;

public final class EntityToProductConverter {
    public EntityToProductConverter(){
    }
    public static Product convert(ProductEntity productEntity){
        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .imagePath(productEntity.getImagePath())
                .descriptionShort(productEntity.getDescriptionShort())
                .descriptionLong(productEntity.getDescriptionLong())
                .weight(productEntity.getWeight())
                .ingredients(productEntity.getIngredients())
                .producer(convertProducer(productEntity.getProducer()))
                .energy(productEntity.getEnergy())
                .fat(productEntity.getFat())
                .carbohydrate(productEntity.getCarbohydrate())
                .sugars(productEntity.getSugars())
                .fibre(productEntity.getFibre())
                .protein(productEntity.getProtein())
                .salt(productEntity.getSalt())
                .build();
    }

    public static Producer convertProducer(ProducerEntity producerToConvert){
        return EntityToProducerConverter.convert(producerToConvert);
    }
}
