package br.edu.infnet.tiago.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "tb_actors")
@Entity
public class Actor extends Person {

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
}