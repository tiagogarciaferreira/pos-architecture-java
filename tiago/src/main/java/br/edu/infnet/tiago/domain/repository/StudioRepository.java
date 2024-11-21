package br.edu.infnet.tiago.domain.repository;

import br.edu.infnet.tiago.domain.model.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long>, JpaSpecificationExecutor<Studio> {

    @NonNull
    @Override
        // @EntityGraph(attributePaths = {"movies", "country"})
    Optional<Studio> findById(@NonNull Long id);
}