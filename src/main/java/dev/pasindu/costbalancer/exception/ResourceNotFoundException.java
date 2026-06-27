package dev.pasindu.costbalancer.exception;

import io.github.og4dev.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException {
    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
