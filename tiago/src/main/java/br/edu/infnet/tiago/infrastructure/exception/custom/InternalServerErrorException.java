package br.edu.infnet.tiago.infrastructure.exception.custom;

public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException(String message) {
        this(message, null);
    }

    public InternalServerErrorException(String message, Throwable throwable) {
        super(message, throwable);
    }
}