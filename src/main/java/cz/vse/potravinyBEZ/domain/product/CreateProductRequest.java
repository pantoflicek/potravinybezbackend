package cz.vse.potravinyBEZ.domain.product;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
    @NotBlank
    @Length(min = 2, max = 200)
    private String name;
    @Length(min = 2, max = 1024)
    private String imagePath;
    @Length(min = 2, max = 300)
    private String descriptionShort;
    @Length(min = 2, max = 1024)
    private String descriptionLong;
    private int weight;
    @Length(min = 2, max = 1024)
    private String ingredients;
    @Length(min = 2, max = 45)
    private String producer;
    private int energy;
    private int fat;
    private int carbohydrate;
    private int sugars;
    private int fibre;
    private int protein;
    private int salt;
}
