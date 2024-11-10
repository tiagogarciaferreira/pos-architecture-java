package br.edu.infnet.tiago.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@With
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "tb_directors")
@Entity
public class Director extends Person {

    @ElementCollection
    @CollectionTable(name = "tb_director_awards", joinColumns = @JoinColumn(name = "director_id"))
    @Column(name = "award_name")
    @Size(max = 100, message = "The list of awards can contain a maximum of 100 items")
    private List<String> awards;

    @OneToMany(mappedBy = "director")
    private List<Movie> movies;
}