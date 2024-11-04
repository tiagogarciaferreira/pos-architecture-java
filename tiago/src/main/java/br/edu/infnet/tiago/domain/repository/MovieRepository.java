package br.edu.infnet.tiago.domain.repository;

import br.edu.infnet.tiago.domain.model.Movie;
import br.edu.infnet.tiago.infrastructure.repository.MovieRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, MovieRepositoryCustom {
}
