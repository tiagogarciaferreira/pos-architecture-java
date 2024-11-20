package br.edu.infnet.tiago.domain.repository;

import br.edu.infnet.tiago.domain.model.Country;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long>, JpaSpecificationExecutor<Country> {

    @NonNull
    @Override
    @EntityGraph(attributePaths = {"movies", "actors", "directors", "studios"})
    Optional<Country> findById(@NonNull Long id);
}