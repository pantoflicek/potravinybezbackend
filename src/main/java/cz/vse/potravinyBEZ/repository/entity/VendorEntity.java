package cz.vse.potravinyBEZ.repository.entity;

//Persistence
import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

//Lombok
import lombok.*;

@Entity
@Table(name = "vendor")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendorEntity {
    @Id
    @Column(name = "id")
    private int id;
    @Length(min = 2, max = 45)
    @Column(name = "name")
    private String name;
    @Length(min = 2, max = 1024)
    @Column(name = "logo_path")
    private String logoPath;
    @Length(min = 2, max = 200)
    @Column(name = "website")
    private String website;
}
