package cz.vse.potravinyBEZ.repository.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "product_tag")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductTagEntity {
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private TagEntity tag;
}
