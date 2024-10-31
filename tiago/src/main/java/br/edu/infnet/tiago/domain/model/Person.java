package br.edu.infnet.tiago.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.OffsetDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static jakarta.persistence.InheritanceType.JOINED;

@Data
@MappedSuperclass
@Inheritance(strategy = JOINED)
public abstract class Person {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private OffsetDateTime birthDate;

    @Transient
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @CreatedDate
    @Column(updatable = false)
    private OffsetDateTime created;

    @LastModifiedDate
    private OffsetDateTime modified;
}