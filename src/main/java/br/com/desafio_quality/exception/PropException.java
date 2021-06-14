package br.com.desafio_quality.exception;

import org.springframework.core.NestedRuntimeException;

// custom exception
public class PropException extends NestedRuntimeException {
    public PropException(String msg) {
        super(msg);
    }
}
