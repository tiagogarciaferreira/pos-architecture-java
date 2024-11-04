package br.edu.infnet.tiago.application.exception.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Violation {

    private final String field;

    private final String message;
}