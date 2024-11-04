package br.edu.infnet.tiago.application.exception.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Problem {

    private final String title;

    private final String detail;

    private final String type;

    private final Integer status;

    private final String instance;

    private final List<Violation> violations;
}
