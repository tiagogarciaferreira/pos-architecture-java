package br.edu.infnet.tiago.v2.application.dto;

import br.edu.infnet.tiago.domain.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDTO {

    private Long id;

    private String imdbId;

    private String title;

    private LocalDate releaseDate;

    private boolean released;

    private int durationMinutes;

    private Float budgetDollars;

    private Double boxOfficeDollars;

    private String synopsis;

    private Genre genre;

    private Director director;

    private Studio studio;

    private Country country;

    private List<Actor> actors;

    private List<Language> languages;

    private List<Language> subtitles;

    private OffsetDateTime created;

    private OffsetDateTime modified;
}