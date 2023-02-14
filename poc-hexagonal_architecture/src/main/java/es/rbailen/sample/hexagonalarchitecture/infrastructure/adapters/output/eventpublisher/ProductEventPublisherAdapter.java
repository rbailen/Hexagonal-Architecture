package es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.output.eventpublisher;

import es.rbailen.sample.hexagonalarchitecture.application.ports.output.ProductEventPublisher;
import es.rbailen.sample.hexagonalarchitecture.domain.event.ProductCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;

public record ProductEventPublisherAdapter(
        ApplicationEventPublisher applicationEventPublisher) implements ProductEventPublisher {

    @Override
    public void publishProductCreatedEvent(ProductCreatedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

}
