package xchange.x_change.exception;

import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(DuplicateKeyException.class)
  protected ResponseEntity<Object> handleDuplicateKeyException(
    DuplicateKeyException ex,
    WebRequest request
  ) {
    return new ResponseEntity<>(
      ExceptionResponse
        .builder()
        .timestamp(LocalDateTime.now())
        .error("Duplicate key value violates unique constraint")
        .message(Map.of("error", ex.getMessage()))
        .build(),
      HttpStatus.BAD_REQUEST
    );
  }
}
