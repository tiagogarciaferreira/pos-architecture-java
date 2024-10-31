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
@Table(name = "tb_country")
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String code;

    @OneToMany(mappedBy = "country", fetch = LAZY)
    private List<Movie> movies;

    @CreatedDate
    @Column(updatable = false)
    private OffsetDateTime created;

    @LastModifiedDate
    private OffsetDateTime modified;
}