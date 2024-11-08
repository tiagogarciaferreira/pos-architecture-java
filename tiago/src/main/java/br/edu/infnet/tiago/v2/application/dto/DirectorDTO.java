package br.edu.infnet.tiago.v2.application.dto;

import br.edu.infnet.tiago.domain.model.Country;
import br.edu.infnet.tiago.domain.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DirectorDTO {

    private Long id;

    private String name;

    private LocalDate dateOfBirth;

    private int age;

    private Country country;

    private List<String> awards;

    private List<Movie> movies;

    private OffsetDateTime created;

    private OffsetDateTime modified;
}