package cz.vse.potravinyBEZ.repository.entity;

//Persistence
import jakarta.persistence.*;

//Lombok
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "allergen")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllergenEntity {
    @Id
    @Column(name = "id")
    private int id;
    @Length(min = 2, max = 45)
    @Column(name = "name")
    private String name;
}
