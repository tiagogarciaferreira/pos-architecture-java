package br.edu.infnet.tiago.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.LocalDate.now;

@With
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_movies")
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Pattern(regexp = "^tt\\d{7,9}$", message = "Invalid IMDb ID format. It should start with 'tt' followed by 7 to 9 digits")
    private String imdbId;

    @DecimalMin(value = "0.0", message = "Imdb rating earnings must be 0 rating or more")
    private Float imdbRating;

    @NotBlank(message = "Title is required")
    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    private String title;

    private String poster;

    @NotNull(message = "Release date is required")
    private LocalDate releaseDate;

    @Transient
    private boolean released;

    @Min(value = 1, message = "Duration must be at least 1 minute")
    private int durationMinutes;

    @DecimalMin(value = "0.0", inclusive = false, message = "Budget must be greater than 0 dollars")
    private Double budgetDollars;

    @DecimalMin(value = "0.0", message = "Box office earnings must be 0 dollars or more")
    private Double boxOfficeDollars;

    @NotBlank(message = "Synopsis is required")
    @Size(min = 10, max = 1000, message = "Synopsis must be between 10 and 1000 characters")
    private String synopsis;

    @NotEmpty(message = "At least one genre is required")
    @ManyToMany
    @JoinTable(name = "tb_movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @NotNull(message = "Director is required")
    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    @NotNull(message = "Studio is required")
    @ManyToOne
    @JoinColumn(name = "studio_id")
    private Studio studio;

    @NotNull(message = "Country is required")
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @NotEmpty(message = "At least one actor is required")
    @ManyToMany
    @JoinTable(name = "tb_movie_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actors;

    @NotEmpty(message = "At least one language is required")
    @ManyToMany
    @JoinTable(name = "tb_movie_languages",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private List<Language> languages;

    @NotEmpty(message = "At least one language is required")
    @ManyToMany
    @JoinTable(name = "tb_movie_subtitles",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private List<Language> subtitles;

    @CreationTimestamp
    private OffsetDateTime created;

    @UpdateTimestamp
    private OffsetDateTime modified;

    @Version
    @NotNull(message = "Version cannot be null")
    @Min(value = 0, message = "Version must be zero or greater")
    private Integer version;

    @PostLoad
    private void postLoad() {
        this.released = releaseDate.isBefore(now());
    }
}