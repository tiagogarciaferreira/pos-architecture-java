package br.edu.infnet.tiago.infrastructure.exception.custom;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        this(message, null);
    }

    public NotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}