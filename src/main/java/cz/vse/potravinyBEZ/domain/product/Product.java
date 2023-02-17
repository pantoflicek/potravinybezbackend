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
    private int weight;
    private String ingredients;
    private Producer producer;
    private int energy;
    private int fat;
    private int carbohydrate;
    private int sugars;
    private int fibre;
    private int protein;
    private int salt;
}
