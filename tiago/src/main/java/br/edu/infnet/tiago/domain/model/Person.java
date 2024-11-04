package br.edu.infnet.tiago.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static jakarta.persistence.InheritanceType.JOINED;
import static java.time.OffsetDateTime.now;
import static java.time.temporal.ChronoUnit.YEARS;

@Data
@MappedSuperclass
@Inheritance(strategy = JOINED)
public abstract class Person {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @NotNull(message = "Date of Birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Transient
    private int age;

    @NotNull(message = "Country is required")
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @CreationTimestamp
    private OffsetDateTime created;

    @UpdateTimestamp
    private OffsetDateTime modified;

    @PostLoad
    private void postLoad() {
        this.age = (int) YEARS.between(dateOfBirth, now());
    }
}