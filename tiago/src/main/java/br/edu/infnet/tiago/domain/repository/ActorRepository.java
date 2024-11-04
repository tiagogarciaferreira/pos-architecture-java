package br.edu.infnet.tiago.domain.repository;

import br.edu.infnet.tiago.domain.model.Actor;
import br.edu.infnet.tiago.infrastructure.repository.ActorRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long>, ActorRepositoryCustom {
}
