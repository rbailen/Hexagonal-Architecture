package es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.output.persistence;

import es.rbailen.sample.hexagonalarchitecture.application.ports.output.ProductOutputPort;
import es.rbailen.sample.hexagonalarchitecture.domain.model.Product;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.output.persistence.mapper.ProductPersistenceMapper;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.output.persistence.repository.ProductRepository;

import java.util.Optional;

public record ProductPersistenceAdapter(ProductRepository productRepository)
        implements ProductOutputPort {

    @Override
    public Product saveProduct(Product product) {
        final var productEntity = ProductPersistenceMapper.INSTANCE.toProductEntity(product);
        final var productE = productRepository.save(productEntity);
        return ProductPersistenceMapper.INSTANCE.toProduct(productE);
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        final var productEntity = productRepository.findById(id);

        if (productEntity.isEmpty()) {
            return Optional.empty();
        }

        Product product = ProductPersistenceMapper.INSTANCE.toProduct(productEntity.get());
        return Optional.of(product);
    }

}
