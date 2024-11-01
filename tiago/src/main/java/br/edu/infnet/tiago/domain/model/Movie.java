package br.edu.infnet.tiago.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.OffsetDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.OffsetDateTime.now;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_movies")
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    private String title;

    @NotNull(message = "Release date is required")
    private OffsetDateTime releaseDate;

    @Transient
    private boolean released;

    @Min(value = 1, message = "Duration must be at least 1 minute")
    private int durationInMinutes;

    @DecimalMin(value = "0.0", inclusive = false, message = "Budget must be greater than 0 dollars")
    private Double budgetInDollars;

    @DecimalMin(value = "0.0", message = "Box office earnings must be 0 dollars or more")
    private double boxOfficeInDollars;

    @NotBlank(message = "Synopsis is required")
    @Size(min = 10, max = 500, message = "Synopsis must be between 10 and 500 characters")
    private String synopsis;

    @NotNull(message = "Genre is required")
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

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
    @JoinTable(name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actors;

    @CreatedDate
    @Column(updatable = false)
    private OffsetDateTime created;

    @LastModifiedDate
    private OffsetDateTime modified;

    @PostLoad
    private void postLoad() {
        this.released = releaseDate.isBefore(now());
    }
}