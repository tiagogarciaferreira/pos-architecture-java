package br.edu.infnet.tiago.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.OffsetDateTime;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_movie")
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;

    private OffsetDateTime releaseDate;

    private int durationInMinutes;

    private Double budgetInDollars;

    private Float boxOfficeInDollars;

    private String synopsis;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    @ManyToOne
    @JoinColumn(name = "studio_id")
    private Studio studio;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actors;

    @CreatedDate
    @Column(updatable = false)
    private OffsetDateTime created;

    @LastModifiedDate
    private OffsetDateTime modified;
}