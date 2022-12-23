package ewp.tasktracker.api;

import ewp.tasktracker.api.dto.ErrorDto;
import ewp.tasktracker.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleInternalServerError(Exception e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, List<String>> errors = new HashMap<>();
        errors.put("errors",
                exception.getBindingResult()
                        .getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage).collect(Collectors.toList()));
        return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleUnprocessableEntity(HttpMessageNotReadableException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ErrorDto(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
