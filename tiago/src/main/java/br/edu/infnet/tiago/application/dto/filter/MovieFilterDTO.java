package br.edu.infnet.tiago.application.dto.filter;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
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

    @Size(max = 100, message = "Title must be at most 100 characters")
    private String title;

    @Size(max = 100, message = "Imdb IDs must contain no more than 100 elements")
    private List<String> imdbIds;

    private LocalDate releaseDateFrom;

    private LocalDate releaseDateTo;

    @Min(value = 1, message = "Duration minutes must be at least 1 if provided")
    private Integer durationMinutesFrom;

    @Min(value = 1, message = "Duration minutes must be at least 1 if provided")
    private Integer durationMinutesTo;

    @DecimalMin(value = "1", message = "Budget dollars must be at least 1 if provided")
    private Float budgetDollarsFrom;

    @DecimalMin(value = "1", message = "Budget dollars must be at least 1 if provided")
    private Float budgetDollarsTo;

    @DecimalMin(value = "1", message = "Box office dollars must be at least 1 if provided")
    private Double boxOfficeDollarsFrom;

    @DecimalMin(value = "1", message = "Box office dollars must be at least 1 if provided")
    private Double boxOfficeDollarsTo;

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