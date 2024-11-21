package br.edu.infnet.tiago.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieFullDTO {

    private Long id;

    private String imdbId;

    private String title;

    private String poster;

    private Float imdbRating;

    private LocalDate releaseDate;

    private boolean released;

    private int durationMinutes;

    private Float budgetDollars;

    private Double boxOfficeDollars;

    private String synopsis;

    private List<GenreDTO> genres;

    private DirectorDTO director;

    private StudioDTO studio;

    private CountryDTO country;

    private List<ActorDTO> actors;

    private List<LanguageDTO> languages;

    private List<LanguageDTO> subtitles;

    private OffsetDateTime created;

    private OffsetDateTime modified;

    private int version;
}