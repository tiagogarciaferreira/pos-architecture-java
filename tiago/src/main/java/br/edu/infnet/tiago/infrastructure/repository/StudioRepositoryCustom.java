package br.edu.infnet.tiago.infrastructure.repository;

import br.edu.infnet.tiago.domain.model.Studio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface StudioRepositoryCustom {

    Page<Studio> find(Specification<Studio> specification, Pageable pageable);
}