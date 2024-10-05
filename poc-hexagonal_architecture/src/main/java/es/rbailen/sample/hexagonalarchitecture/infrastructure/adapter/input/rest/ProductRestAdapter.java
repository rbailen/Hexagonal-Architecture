package es.rbailen.sample.hexagonalarchitecture.infrastructure.adapter.input.rest;

import es.rbailen.sample.hexagonalarchitecture.application.port.input.GetProductUseCase;
import es.rbailen.sample.hexagonalarchitecture.domain.model.Product;
import es.rbailen.sample.hexagonalarchitecture.application.port.input.CreateProductUseCase;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapter.input.rest.data.request.ProductCreateRequest;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapter.input.rest.data.response.ProductCreateResponse;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapter.input.rest.data.response.ProductQueryResponse;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapter.input.rest.mapper.ProductRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ProductRestAdapter {

    private final CreateProductUseCase createProductUseCase;

    private final GetProductUseCase getProductUseCase;

    private final ProductRestMapper productRestMapper;

    @PostMapping(value = "/products")
    public ResponseEntity<ProductCreateResponse> createProduct(@RequestBody @Valid final ProductCreateRequest productCreateRequest){
        // Request to domain
        Product product = this.productRestMapper.toProduct(productCreateRequest);

        product = this.createProductUseCase.createProduct(product);

        // Domain to response
        return new ResponseEntity<>(this.productRestMapper.toProductCreateResponse(product), HttpStatus.CREATED);
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<ProductQueryResponse> getProduct(@PathVariable final Long id){
        final Product product = this.getProductUseCase.getProductById(id);
        return new ResponseEntity<>(this.productRestMapper.toProductQueryResponse(product), HttpStatus.OK);
    }

}
