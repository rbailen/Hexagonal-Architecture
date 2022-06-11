package es.rbailen.sample.hexagonalarchitecture.application.ports.input;

import es.rbailen.sample.hexagonalarchitecture.domain.model.Product;

public interface GetProductUseCase {

    Product getProductById(Long id);

}
