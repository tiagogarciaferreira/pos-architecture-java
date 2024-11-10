package br.edu.infnet.tiago.application.dto.filter;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieFilterDTO {

    @Size(max = 100, message = "Movie IDs must contain no more than 100 elements")
    private List<Long> ids;

    private String title;

    @Size(max = 100, message = "Imdb IDs must contain no more than 100 elements")
    private List<String> imdbIds;

    private LocalDate releaseDateFrom;

    private LocalDate releaseDateTo;

    private int durationMinutesFrom;

    private int durationMinutesTo;

    private float budgetDollarsFrom;

    private float budgetDollarsTo;

    private double boxOfficeDollarsFrom;

    private double boxOfficeDollarsTo;

    @Size(max = 100, message = "Actor IDs must contain no more than 100 elements")
    private List<Long> actorIds;

    @Size(max = 100, message = "Studio IDs must contain no more than 100 elements")
    private List<Long> studioIds;

    @Size(max = 100, message = "Director IDs must contain no more than 100 elements")
    private List<Long> directorIds;

    @Size(max = 100, message = "Genre IDs must contain no more than 100 elements")
    private List<Long> genreIds;

    @Size(max = 100, message = "Country IDs must contain no more than 100 elements")
    private List<Long> countryIds;

    @Size(max = 100, message = "Language IDs must contain no more than 100 elements")
    private List<Long> languageIds;

    @Size(max = 100, message = "Subtitle IDs must contain no more than 100 elements")
    private List<Long> subtitleIds;
}