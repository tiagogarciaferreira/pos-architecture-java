package br.edu.infnet.tiago.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@With
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_countries")
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank(message = "Country name is required")
    @Size(min = 3, max = 50, message = "Country name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Country code is required")
    @Size(min = 3, max = 3, message = "Country code must be exactly 3 characters")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Country code must be uppercase letters, exactly 3 characters")
    private String code;

    @OneToMany(mappedBy = "country")
    private List<Movie> movies;

    @OneToMany(mappedBy = "country")
    private List<Actor> actors;

    @OneToMany(mappedBy = "country")
    private List<Director> directors;

    @OneToMany(mappedBy = "country")
    private List<Studio> studios;

    @CreationTimestamp
    private OffsetDateTime created;

    @UpdateTimestamp
    private OffsetDateTime modified;

    @Version
    @NotNull(message = "Version cannot be null")
    @Min(value = 0, message = "Version must be greater than or equal to zero")
    private int version;
}