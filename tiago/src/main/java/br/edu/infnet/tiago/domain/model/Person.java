package br.edu.infnet.tiago.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@MappedSuperclass
public abstract class Person {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private OffsetDateTime birthDate;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country nationality;
}