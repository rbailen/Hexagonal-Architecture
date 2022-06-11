package es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.output.persistence.mapper;

import es.rbailen.sample.hexagonalarchitecture.domain.model.Product;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.output.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ProductPersistenceMapper {

    ProductEntity toProductEntity(Product product);

    Product toProduct(ProductEntity productEntity);

}
