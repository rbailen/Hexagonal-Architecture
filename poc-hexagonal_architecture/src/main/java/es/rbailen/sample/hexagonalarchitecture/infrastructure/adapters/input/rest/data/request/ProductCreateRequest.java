package es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.input.rest.data.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateRequest {

    @NotEmpty(message = "Name may not be empty")
    private String name;

    @NotEmpty(message = "Description may not be empty")
    private String description;

}
