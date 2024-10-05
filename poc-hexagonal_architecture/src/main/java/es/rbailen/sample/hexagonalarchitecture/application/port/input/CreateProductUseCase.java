package es.rbailen.sample.hexagonalarchitecture.application.port.input;

import es.rbailen.sample.hexagonalarchitecture.domain.model.Product;

public interface CreateProductUseCase {

    Product createProduct(Product product);

}
