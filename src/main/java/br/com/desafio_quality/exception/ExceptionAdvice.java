package br.com.desafio_quality.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler({PropException.class})
    public ResponseEntity hadleExcept (PropException p) {
        return new ResponseEntity(p.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
