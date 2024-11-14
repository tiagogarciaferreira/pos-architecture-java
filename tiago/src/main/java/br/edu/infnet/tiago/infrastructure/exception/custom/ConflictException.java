package br.edu.infnet.tiago.infrastructure.exception.custom;

public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        this(message, null);
    }

    public ConflictException(String message, Throwable throwable) {
        super(message, throwable);
    }
}