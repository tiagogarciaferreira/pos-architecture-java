package br.edu.infnet.tiago.infrastructure.exception.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Violation {

    private final String field;

    private final String message;
}