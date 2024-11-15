package br.edu.infnet.tiago.application.dto;

import br.edu.infnet.tiago.domain.model.Country;
import br.edu.infnet.tiago.domain.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudioFullDTO {

    private Long id;

    private String name;

    private Country country;

    private List<Movie> movies;

    private OffsetDateTime created;

    private OffsetDateTime modified;
}