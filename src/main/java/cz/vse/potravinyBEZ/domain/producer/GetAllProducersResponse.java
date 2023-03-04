package cz.vse.potravinyBEZ.domain.producer;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProducersResponse {
    private List<Producer> producers;
}
