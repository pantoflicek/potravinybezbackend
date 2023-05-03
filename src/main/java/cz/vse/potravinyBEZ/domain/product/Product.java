package cz.vse.potravinyBEZ.domain.product;

import cz.vse.potravinyBEZ.domain.producer.Producer;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Product {
    private int id;
    private String name;
    private String imagePath;
    private String descriptionShort;
    private String descriptionLong;
    private float weight;
    private String ingredients;
    private Producer producer;
    private float energy;
    private float fat;
    private float carbohydrate;
    private float sugars;
    private float fibre;
    private float protein;
    private float salt;
}
