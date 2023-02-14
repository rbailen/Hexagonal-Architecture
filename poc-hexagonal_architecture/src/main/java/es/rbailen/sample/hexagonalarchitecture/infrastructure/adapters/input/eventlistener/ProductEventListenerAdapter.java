package es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.input.eventlistener;

import es.rbailen.sample.hexagonalarchitecture.domain.event.ProductCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductEventListenerAdapter {

    private final Logger log = LoggerFactory.getLogger(ProductEventListenerAdapter.class);

    @EventListener
    public void handle(ProductCreatedEvent event) {
        if (log.isInfoEnabled()) {
            log.info(
                    """ 
                            Product created with id %d at %s
                            """.formatted(event.id(), event.date())
            );
        }

    }

}
