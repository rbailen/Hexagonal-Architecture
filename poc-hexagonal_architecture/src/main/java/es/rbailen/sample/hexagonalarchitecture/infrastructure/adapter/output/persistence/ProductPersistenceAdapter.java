package es.rbailen.sample.hexagonalarchitecture.infrastructure.adapter.output.persistence;

import es.rbailen.sample.hexagonalarchitecture.application.port.output.ProductOutputPort;
import es.rbailen.sample.hexagonalarchitecture.domain.model.Product;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapter.output.persistence.entity.ProductEntity;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapter.output.persistence.mapper.ProductPersistenceMapper;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapter.output.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductOutputPort {

    private final ProductRepository productRepository;

    private final ProductPersistenceMapper productPersistenceMapper;

    @Override
    public Product saveProduct(final Product product) {
        ProductEntity productEntity = this.productPersistenceMapper.toProductEntity(product);
        productEntity = this.productRepository.save(productEntity);
        return this.productPersistenceMapper.toProduct(productEntity);
    }

    @Override
    public Optional<Product> getProductById(final Long id) {
        final Optional<ProductEntity> productEntity = this.productRepository.findById(id);

        if(productEntity.isEmpty()) {
            return Optional.empty();
        }

        final Product product = this.productPersistenceMapper.toProduct(productEntity.get());
        return Optional.of(product);
    }

}
