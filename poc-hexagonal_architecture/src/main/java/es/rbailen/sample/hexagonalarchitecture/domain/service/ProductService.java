package es.rbailen.sample.hexagonalarchitecture.domain.service;

import es.rbailen.sample.hexagonalarchitecture.application.ports.input.GetProductUseCase;
import es.rbailen.sample.hexagonalarchitecture.application.ports.output.ProductEventPublisher;
import es.rbailen.sample.hexagonalarchitecture.application.ports.output.ProductOutputPort;
import es.rbailen.sample.hexagonalarchitecture.domain.event.ProductCreatedEvent;
import es.rbailen.sample.hexagonalarchitecture.domain.exception.ProductNotFound;
import es.rbailen.sample.hexagonalarchitecture.domain.model.Product;
import es.rbailen.sample.hexagonalarchitecture.application.ports.input.CreateProductUseCase;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class ProductService implements CreateProductUseCase, GetProductUseCase {

    private final ProductOutputPort productOutputPort;

    private final ProductEventPublisher productEventPublisher;

    @Override
    public Product createProduct(Product product) {
        product = productOutputPort.saveProduct(product);
        productEventPublisher.publishProductCreatedEvent(new ProductCreatedEvent(product.getId()));
        return product;
    }

    @Override
    public Product getProductById(Long id) {
        return productOutputPort.getProductById(id).orElseThrow(() -> new ProductNotFound("Product not found with id " + id));
    }

}
