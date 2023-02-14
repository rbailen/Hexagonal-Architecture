package es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.output.persistence.mapper;

import es.rbailen.sample.hexagonalarchitecture.domain.model.Product;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.output.persistence.entity.ProductE;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductPersistenceMapper {

    ProductPersistenceMapper INSTANCE = Mappers.getMapper(ProductPersistenceMapper.class);

    ProductE toProductEntity(Product product);

    Product toProduct(ProductE productEntity);

}
