package es.rbailen.sample.hexagonalarchitecture.infrastructure.adapter.output.eventpublisher;

import es.rbailen.sample.hexagonalarchitecture.application.port.output.ProductEventPublisher;
import es.rbailen.sample.hexagonalarchitecture.domain.event.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class ProductEventPublisherAdapter implements ProductEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishProductCreatedEvent(final ProductCreatedEvent event) {
        this.applicationEventPublisher.publishEvent(event);
    }

}
