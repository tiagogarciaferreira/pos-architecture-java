package br.edu.infnet.tiago.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "tb_actor")
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Actor extends Person {

    @ManyToMany(mappedBy = "actors", fetch = LAZY)
    private List<Movie> movies;
}