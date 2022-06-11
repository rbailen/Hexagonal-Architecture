package es.rbailen.sample.hexagonalarchitecture.application.ports.output;

import es.rbailen.sample.hexagonalarchitecture.domain.event.ProductCreatedEvent;

public interface ProductEventPublisher {

    void publishProductCreatedEvent(ProductCreatedEvent event);

}
