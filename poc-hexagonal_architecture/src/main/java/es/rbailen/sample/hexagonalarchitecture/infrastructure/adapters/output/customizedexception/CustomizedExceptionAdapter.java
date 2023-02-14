package es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.output.customizedexception;

import es.rbailen.sample.hexagonalarchitecture.domain.exception.ProductNotFound;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.output.customizedexception.data.response.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class CustomizedExceptionAdapter extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), Arrays.asList(request.getDescription(false)));

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    @ExceptionHandler(ProductNotFound.class)
    public final ResponseEntity<Object> handleUserNotFoundException(ProductNotFound ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), Arrays.asList(request.getDescription(false)));

        return ResponseEntity.status(NOT_FOUND).body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().stream().forEach(error -> errors.add(error.getDefaultMessage()));

        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "Validation Failed", errors);

        return ResponseEntity.status(BAD_REQUEST).body(exceptionResponse);
    }
}