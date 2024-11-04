package br.edu.infnet.tiago.domain.repository;

import br.edu.infnet.tiago.domain.model.Studio;
import br.edu.infnet.tiago.infrastructure.repository.StudioRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long>, StudioRepositoryCustom {
}
