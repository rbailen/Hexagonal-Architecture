package es.rbailen.sample.hexagonalarchitecture.infrastructure.adapter.input.rest.data.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductQueryResponse {

    private Long id;

    private String name;

    private String description;

}
