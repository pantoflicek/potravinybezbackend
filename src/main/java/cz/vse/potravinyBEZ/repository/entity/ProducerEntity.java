package cz.vse.potravinyBEZ.repository.entity;

//Persistence
import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

//Lombok
import lombok.*;

@Entity
@Table(name = "producer")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProducerEntity {
    @Id
    @Column(name = "id")
    private int id;
    @Length(min = 2, max = 45)
    @Column(name = "name")
    private String name;
}
