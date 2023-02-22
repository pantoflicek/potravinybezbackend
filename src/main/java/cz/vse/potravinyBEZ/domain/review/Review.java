package cz.vse.potravinyBEZ.domain.review;
import cz.vse.potravinyBEZ.domain.product.Product;
import cz.vse.potravinyBEZ.domain.user.User;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Review {
    private int id;
    private Date date;
    private String text;
    private int stars;
    private Product product;
    private User user;
}
