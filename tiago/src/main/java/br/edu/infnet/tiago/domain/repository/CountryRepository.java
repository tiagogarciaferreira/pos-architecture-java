package br.edu.infnet.tiago.domain.repository;

import br.edu.infnet.tiago.domain.model.Country;
import br.edu.infnet.tiago.infrastructure.repository.CountryRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long>, CountryRepositoryCustom {
}
