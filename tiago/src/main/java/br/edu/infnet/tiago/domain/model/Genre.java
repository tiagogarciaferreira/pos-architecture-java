package br.edu.infnet.tiago.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.OffsetDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_genre")
@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "genre", cascade = ALL, fetch = LAZY)
    private List<Movie> movies;

    @CreatedDate
    @Column(updatable = false)
    private OffsetDateTime created;

    @LastModifiedDate
    private OffsetDateTime modified;
}