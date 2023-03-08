package cz.vse.potravinyBEZ.domain.tag;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Tag {
    private int id;
    private String name;
}
