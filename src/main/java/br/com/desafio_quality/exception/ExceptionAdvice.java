package br.com.desafio_quality.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionAdvice {

    // handle to dto validation and custom exception
    @ExceptionHandler({MethodArgumentNotValidException.class, PropException.class})
    public ResponseEntity handleException(Exception e){

        String msg = e.getMessage();
        Map<String, String> errors = new HashMap<>();
        if (e instanceof MethodArgumentNotValidException){
            ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
    }

    // handle if request body is null
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity handleRequestBody(Exception e){
        String msg = "Required request body is missing";
        return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
    }
}
