package es.rbailen.sample.hexagonalarchitecture.application.service;

import es.rbailen.sample.hexagonalarchitecture.application.port.input.GetProductUseCase;
import es.rbailen.sample.hexagonalarchitecture.application.port.output.ProductEventPublisher;
import es.rbailen.sample.hexagonalarchitecture.application.port.output.ProductOutputPort;
import es.rbailen.sample.hexagonalarchitecture.domain.event.ProductCreatedEvent;
import es.rbailen.sample.hexagonalarchitecture.domain.exception.ProductNotFound;
import es.rbailen.sample.hexagonalarchitecture.domain.model.Product;
import es.rbailen.sample.hexagonalarchitecture.application.port.input.CreateProductUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductService implements CreateProductUseCase, GetProductUseCase {

    private final ProductOutputPort productOutputPort;

    private final ProductEventPublisher productEventPublisher;

    @Override
    public Product createProduct(Product product) {
        product = this.productOutputPort.saveProduct(product);
        this.productEventPublisher.publishProductCreatedEvent(new ProductCreatedEvent(product.getId()));
        return product;
    }

    @Override
    public Product getProductById(final Long id) {
        return this.productOutputPort.getProductById(id).orElseThrow(() -> new ProductNotFound("Product not found with id " + id));
    }

}
