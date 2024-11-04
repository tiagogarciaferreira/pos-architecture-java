package br.edu.infnet.tiago.application.specification;

import br.edu.infnet.tiago.application.dto.filter.CountryFilterDTO;
import br.edu.infnet.tiago.domain.model.Country;
import org.springframework.data.jpa.domain.Specification;

public class CountrySpecification {

    public static Specification<Country> create(CountryFilterDTO filter) {
        return (root, query, criteriaBuilder) -> {
            Specification<Country> specification = Specification.where(null);
            return specification.toPredicate(root, query, criteriaBuilder);
        };
    }
}