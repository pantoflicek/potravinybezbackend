package cz.vse.potravinyBEZ.domain.offer;

import cz.vse.potravinyBEZ.domain.product.Product;
import cz.vse.potravinyBEZ.domain.vendor.Vendor;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Offer {
    private int id;
    private int price;
    private Product product;
    private Vendor vendor;
}
