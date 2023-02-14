package es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.output.customizedexception.data.response;

import java.time.LocalDateTime;
import java.util.List;


public record ExceptionResponse(LocalDateTime date, String message, List<String> details) {
}

