package br.edu.infnet.tiago.v2.application.dto;

import br.edu.infnet.tiago.domain.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LanguageDTO {

    private Long id;

    private String name;

    private List<Movie> movies;

    private OffsetDateTime created;

    private OffsetDateTime modified;
}