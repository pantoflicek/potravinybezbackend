package cz.vse.potravinyBEZ.repository.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "offer")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfferEntity {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "price")
    private int price;
    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private VendorEntity vendor;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
}
