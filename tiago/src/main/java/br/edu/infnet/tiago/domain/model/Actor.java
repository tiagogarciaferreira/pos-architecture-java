package br.edu.infnet.tiago.domain.model;

import jakarta.persistence.*;
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
@PrimaryKeyJoinColumn(name = "id")
public class Actor extends Person {

    @ElementCollection
    @CollectionTable(name = "actor_roles", joinColumns = @JoinColumn(name = "actor_id"))
    @Column(name = "role_name")
    private List<String> roles;

    @ElementCollection
    @CollectionTable(name = "tb_actor_awards", joinColumns = @JoinColumn(name = "actor_id"))
    @Column(name = "award_name")
    private List<String> awards;

    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;
}