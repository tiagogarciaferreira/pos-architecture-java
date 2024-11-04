package br.edu.infnet.tiago.domain.repository;

import br.edu.infnet.tiago.domain.model.Genre;
import br.edu.infnet.tiago.infrastructure.repository.GenreRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>, GenreRepositoryCustom {
}
