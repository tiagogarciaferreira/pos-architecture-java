package br.edu.infnet.tiago.infrastructure.repository;

import br.edu.infnet.tiago.domain.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface GenreRepositoryCustom {

    Page<Genre> find(Specification<Genre> specification, Pageable pageable);
}