package cz.vse.potravinyBEZ.repository.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "product_allergen")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductAllergenEntity {
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
    @ManyToOne
    @JoinColumn(name = "alergen_id")
    private AllergenEntity allergen;
}
