package cz.vse.potravinyBEZ.domain.offer;
import cz.vse.potravinyBEZ.domain.product.Product;
import cz.vse.potravinyBEZ.domain.vendor.Vendor;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Offer {
    private int id;
    private int price;
    private Date dateAdded;
    private Date dateTo;
    private Date dateFrom;
    private Product product;
    private Vendor vendor;
}
