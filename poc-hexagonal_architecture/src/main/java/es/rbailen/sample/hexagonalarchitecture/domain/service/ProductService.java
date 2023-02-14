package es.rbailen.sample.hexagonalarchitecture.domain.service;

import es.rbailen.sample.hexagonalarchitecture.application.ports.input.CreateProductUseCase;
import es.rbailen.sample.hexagonalarchitecture.application.ports.input.GetProductUseCase;
import es.rbailen.sample.hexagonalarchitecture.application.ports.output.ProductEventPublisher;
import es.rbailen.sample.hexagonalarchitecture.application.ports.output.ProductOutputPort;
import es.rbailen.sample.hexagonalarchitecture.domain.event.ProductCreatedEvent;
import es.rbailen.sample.hexagonalarchitecture.domain.exception.ProductNotFound;
import es.rbailen.sample.hexagonalarchitecture.domain.model.Product;


public record ProductService(ProductOutputPort productOutputPort,
                             ProductEventPublisher productEventPublisher)
        implements CreateProductUseCase, GetProductUseCase {

    @Override
    public Product createProduct(Product product) {
        product = productOutputPort.saveProduct(product);
        productEventPublisher.publishProductCreatedEvent(new ProductCreatedEvent(product.id()));
        return product;
    }

    @Override
    public Product getProductById(Integer id) {
        return productOutputPort.getProductById(id).orElseThrow(() -> new ProductNotFound("Product not found with id " + id));
    }

}
