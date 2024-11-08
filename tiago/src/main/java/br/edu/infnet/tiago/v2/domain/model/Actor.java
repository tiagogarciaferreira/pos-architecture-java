package br.edu.infnet.tiago.v2.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "tb_actors")
@Entity
public class Actor extends Person {

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

    @ElementCollection
    @CollectionTable(name = "actor_roles", joinColumns = @JoinColumn(name = "actor_id"))
    @Column(name = "role_name")
    @Size(max = 100, message = "The list of roles can contain a maximum of 100 items")
    private List<String> roles;

    @ElementCollection
    @CollectionTable(name = "tb_actor_awards", joinColumns = @JoinColumn(name = "actor_id"))
    @Column(name = "award_name")
    @Size(max = 100, message = "The list of awards can contain a maximum of 100 items")
    private List<String> awards;

    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;

    @CreationTimestamp
    private OffsetDateTime created;

    @UpdateTimestamp
    private OffsetDateTime modified;
}