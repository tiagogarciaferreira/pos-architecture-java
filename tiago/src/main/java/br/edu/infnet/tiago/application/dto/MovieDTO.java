package br.edu.infnet.tiago.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDTO {

    private Long id;

    private String imdbId;

    private String title;

    private String poster;

    private Float imdbRating;

    private LocalDate releaseDate;

    private boolean released;

    private CountryDTO country;

    private int durationMinutes;

    private Float budgetDollars;

    private Double boxOfficeDollars;

    private String synopsis;
}