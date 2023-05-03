package cz.vse.potravinyBEZ.domain.producer;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Producer {
    private int id;
    private String name;
}
