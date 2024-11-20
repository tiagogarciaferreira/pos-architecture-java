package br.edu.infnet.tiago.domain.repository;

import br.edu.infnet.tiago.domain.model.Language;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long>, JpaSpecificationExecutor<Language> {

    @NonNull
    @Override
    @EntityGraph(attributePaths = {"movies"})
    Optional<Language> findById(@NonNull Long id);
}