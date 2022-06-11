package es.rbailen.sample.hexagonalarchitecture.application.ports.output;

import es.rbailen.sample.hexagonalarchitecture.domain.model.Product;

import java.util.Optional;

public interface ProductOutputPort {

    Product saveProduct(Product product);

    Optional<Product> getProductById(Long id);

}
