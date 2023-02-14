package es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.input.rest;

import es.rbailen.sample.hexagonalarchitecture.application.ports.input.CreateProductUseCase;
import es.rbailen.sample.hexagonalarchitecture.application.ports.input.GetProductUseCase;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.input.rest.data.request.ProductCreateRequest;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.input.rest.data.response.ProductCreateResponse;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.input.rest.data.response.ProductQueryResponse;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.input.rest.mapper.ProductRestMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1")
public record ProductRestAdapter(CreateProductUseCase createProductUseCase,
                                 GetProductUseCase getProductUseCase) {

    @PostMapping(value = "/products")
    public ResponseEntity<ProductCreateResponse> createProduct(@RequestBody ProductCreateRequest productCreateRequest) {
        // Request to domain
        var product = ProductRestMapper.INSTANCE.toProduct(productCreateRequest);

        product = createProductUseCase.createProduct(product);

        // Domain to response
        return new ResponseEntity<>(ProductRestMapper.INSTANCE.toProductCreateResponse(product), HttpStatus.CREATED);
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<ProductQueryResponse> getProduct(@PathVariable Integer id) {
        final var product = getProductUseCase.getProductById(id);
        return new ResponseEntity<>(ProductRestMapper.INSTANCE.toProductQueryResponse(product), HttpStatus.OK);
    }

}
