package br.edu.infnet.tiago.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(name = "tb_languages")
@Entity
public class Language {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank(message = "Language name is required")
    @Size(min = 3, max = 50, message = "Language name must be between 3 and 50 characters")
    private String name;

    @ManyToMany(mappedBy = "languages")
    private List<Movie> movies;

    @CreationTimestamp
    private OffsetDateTime created;

    @UpdateTimestamp
    private OffsetDateTime modified;

    @Version
    @NotNull(message = "Version cannot be null")
    @Min(value = 0, message = "Version must be greater than or equal to zero")
    private int version;
}