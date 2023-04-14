package cz.vse.potravinyBEZ.repository.entity;

import jakarta.persistence.*;

import lombok.*;

import java.util.Date;

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
    @Column(name = "date_added")
    private Date dateAdded;
    @Column(name = "date_to")
    private Date dateTo;
    @Column(name = "date_from")
    private Date dateFrom;
    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private VendorEntity vendor;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
}
