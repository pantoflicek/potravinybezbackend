package cz.vse.potravinyBEZ.repository.entity;

//Persistence
import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

//Lombok
import lombok.*;

//Java
import java.util.Date;

@Entity
@Table(name = "review")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewEntity {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "date")
    private Date date;
    @Length(min = 2, max = 1024)
    @Column(name = "text")
    private String text;
    @Column(name = "stars")
    private int stars;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
