package es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.output.eventpublisher;

import es.rbailen.sample.hexagonalarchitecture.application.ports.output.ProductEventPublisher;
import es.rbailen.sample.hexagonalarchitecture.domain.event.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class ProductEventPublisherAdapter implements ProductEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishProductCreatedEvent(ProductCreatedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

}
