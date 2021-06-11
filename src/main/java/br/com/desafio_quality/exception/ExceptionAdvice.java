package br.com.desafio_quality.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionAdvice {
//    @ExceptionHandler({PropException.class})
//    public ResponseEntity hadleExcept (PropException p) {
//        return new ResponseEntity(p.getMessage(), HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler({MethodArgumentNotValidException.class, PropException.class})
    public ResponseEntity<?> handleException(Exception e){

        String msg = e.getMessage();
        if (e instanceof MethodArgumentNotValidException){

            Map<String, String> errors = new HashMap<>();
            ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }
}
