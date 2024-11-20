package br.edu.infnet.tiago.domain.repository;

import br.edu.infnet.tiago.domain.model.Director;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long>, JpaSpecificationExecutor<Director> {

    @NonNull
    @Override
    @EntityGraph(attributePaths = {"awards", "movies"})
    Optional<Director> findById(@NonNull Long id);
}
