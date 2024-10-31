package br.edu.infnet.tiago.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "tb_director")
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Director extends Person {

    @OneToMany(mappedBy = "director", cascade = ALL, fetch = LAZY)
    private List<Movie> movies;
}