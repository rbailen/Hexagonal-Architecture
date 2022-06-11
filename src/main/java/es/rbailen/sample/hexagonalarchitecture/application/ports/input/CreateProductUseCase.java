package es.rbailen.sample.hexagonalarchitecture.application.ports.input;

import es.rbailen.sample.hexagonalarchitecture.domain.model.Product;

public interface CreateProductUseCase {

    Product createProduct(Product product);

}
