package es.rbailen.sample.hexagonalarchitecture.application.port.input;

import es.rbailen.sample.hexagonalarchitecture.domain.model.Product;

public interface GetProductUseCase {

    Product getProductById(Long id);

}
