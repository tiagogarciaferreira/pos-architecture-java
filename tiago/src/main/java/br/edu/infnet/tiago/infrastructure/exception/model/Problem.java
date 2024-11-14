package br.edu.infnet.tiago.infrastructure.exception.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;

@With
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Problem {

    private String title;

    private String detail;

    private String type = "about:blank";

    private Integer status;

    private String instance;

    private List<Violation> violations;
}