package br.edu.infnet.tiago.domain.repository;

import br.edu.infnet.tiago.domain.model.Genre;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>, JpaSpecificationExecutor<Genre> {

    @NonNull
    @Override
    @EntityGraph(attributePaths = {"movies"})
    Optional<Genre> findById(@NonNull Long id);
}
