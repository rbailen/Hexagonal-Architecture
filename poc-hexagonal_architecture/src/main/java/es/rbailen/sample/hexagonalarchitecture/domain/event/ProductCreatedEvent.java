package es.rbailen.sample.hexagonalarchitecture.domain.event;

import java.time.LocalDateTime;


public record ProductCreatedEvent(Integer id, LocalDateTime date) {

    public ProductCreatedEvent(Integer id) {
        this(id, LocalDateTime.now());
    }

}

