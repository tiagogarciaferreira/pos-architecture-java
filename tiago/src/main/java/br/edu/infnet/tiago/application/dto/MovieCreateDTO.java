package br.edu.infnet.tiago.application.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieCreateDTO {

    @Pattern(regexp = "^tt\\d{7,9}$", message = "Invalid IMDb ID format. It should start with 'tt' followed by 7 to 9 digits")
    private String imdbId;

    @NotBlank(message = "Title is required")
    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    private String title;

    @DecimalMin(value = "0.0", inclusive = false, message = "Budget must be greater than 0 dollars")
    private Double budgetDollars;

    @NotBlank(message = "Synopsis is required")
    @Size(min = 10, max = 1000, message = "Synopsis must be between 10 and 1000 characters")
    private String synopsis;

    @NotNull(message = "Genre IDs cannot be null")
    @NotEmpty(message = "Genre IDs cannot be empty")
    @Size(min = 1, max = 10, message = "Genre IDs must contain between 1 and 10 elements")
    private List<Long> genreIds;

    @NotNull(message = "Director ID cannot be null")
    @Min(value = 1, message = "Director ID must be greater than zero")
    private Long directorId;

    @NotNull(message = "Studio ID cannot be null")
    @Min(value = 1, message = "Studio ID must be greater than zero")
    private Long studioId;

    @NotNull(message = "Country ID cannot be null")
    @Min(value = 1, message = "Country ID must be greater than zero")
    private Long countryId;

    @NotNull(message = "Actor IDs cannot be null")
    @NotEmpty(message = "Actor IDs cannot be empty")
    @Size(min = 1, max = 100, message = "Actor IDs must contain between 1 and 100 elements")
    private List<Long> actorIds;

    @NotNull(message = "Language IDs cannot be null")
    @NotEmpty(message = "Language IDs cannot be empty")
    @Size(min = 1, max = 50, message = "Language IDs must contain between 1 and 50 elements")
    private List<Long> languageIds;

    private List<Long> subtitleIds;
}