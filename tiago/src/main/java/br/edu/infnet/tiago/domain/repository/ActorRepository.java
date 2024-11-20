package br.edu.infnet.tiago.domain.repository;

import br.edu.infnet.tiago.domain.model.Actor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long>, JpaSpecificationExecutor<Actor> {

    @NonNull
    @Override
    @EntityGraph(attributePaths = {"roles", "awards", "movies"})
    Optional<Actor> findById(@NonNull Long id);
}
