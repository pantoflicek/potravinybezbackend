package cz.vse.potravinyBEZ.repository.entity;

//persistence
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.validator.constraints.Length;

//lombok
import lombok.*;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @Column(name = "id")
    private int id;

    @Length(min = 2, max = 45)
    @Column(name = "username")
    private String username;

    @Length(min = 2, max = 60)
    @Column(name = "password")
    private String password;
}
