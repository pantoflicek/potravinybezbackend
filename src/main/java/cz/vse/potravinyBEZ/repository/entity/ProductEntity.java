package cz.vse.potravinyBEZ.repository.entity;

import jakarta.persistence.*;

import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    @Column(name = "id")
    private int id;
    @Length(min = 2, max = 200)
    @Column(name = "name")
    private String name;
    @Length(min = 2, max = 1024)
    @Column(name = "image_path")
    private String imagePath;
    @Length(min = 2, max = 300)
    @Column(name = "description_short")
    private String descriptionShort;
    @Length(min = 2, max = 1024)
    @Column(name = "description_long")
    private String descriptionLong;
    @Column(name = "weight")
    private float weight;
    @Length(min = 2, max = 1024)
    @Column(name = "ingredients")
    private String ingredients;
    @ManyToOne
    @JoinColumn(name = "producer_id")
    private ProducerEntity producer;
    @Column(name = "energy")
    private float energy;
    @Column(name = "fat")
    private float fat;
    @Column(name = "carbohydrate")
    private float carbohydrate;
    @Column(name = "sugars")
    private float sugars;
    @Column(name = "fibre")
    private float fibre;
    @Column(name = "protein")
    private float protein;
    @Column(name = "salt")
    private float salt;
}
