package cz.vse.potravinyBEZ.domain.role;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Role {
    private int id;
    private String name;
}
