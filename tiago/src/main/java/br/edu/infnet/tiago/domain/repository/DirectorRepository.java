package br.edu.infnet.tiago.domain.repository;

import br.edu.infnet.tiago.domain.model.Director;
import br.edu.infnet.tiago.infrastructure.repository.DirectorRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long>, DirectorRepositoryCustom {
}
