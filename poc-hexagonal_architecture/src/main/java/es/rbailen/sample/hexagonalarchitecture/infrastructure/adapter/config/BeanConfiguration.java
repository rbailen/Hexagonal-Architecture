package es.rbailen.sample.hexagonalarchitecture.infrastructure.adapter.config;

import es.rbailen.sample.hexagonalarchitecture.application.service.ProductService;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapter.output.eventpublisher.ProductEventPublisherAdapter;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapter.output.persistence.ProductPersistenceAdapter;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapter.output.persistence.mapper.ProductPersistenceMapper;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapter.output.persistence.repository.ProductRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ProductPersistenceAdapter productPersistenceAdapter(final ProductRepository productRepository, final ProductPersistenceMapper productPersistenceMapper) {
        return new ProductPersistenceAdapter(productRepository, productPersistenceMapper);
    }

    @Bean
    public ProductEventPublisherAdapter productEventPublisherAdapter(final ApplicationEventPublisher applicationEventPublisher) {
        return new ProductEventPublisherAdapter(applicationEventPublisher);
    }

    @Bean
    public ProductService productService(final ProductPersistenceAdapter productPersistenceAdapter, final ProductEventPublisherAdapter productEventPublisherAdapter) {
        return new ProductService(productPersistenceAdapter, productEventPublisherAdapter);
    }

}
