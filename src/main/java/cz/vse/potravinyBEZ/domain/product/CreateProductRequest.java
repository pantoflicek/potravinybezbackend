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
    private float weight;
    @Length(min = 2, max = 1024)
    private String ingredients;
    @Length(min = 2, max = 45)
    private String producer;
    private float energy;
    private float fat;
    private float carbohydrate;
    private float sugars;
    private float fibre;
    private float protein;
    private float salt;
}
