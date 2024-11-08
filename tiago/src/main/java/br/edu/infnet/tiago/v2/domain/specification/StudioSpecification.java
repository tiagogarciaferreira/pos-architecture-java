package br.edu.infnet.tiago.v2.domain.specification;

import br.edu.infnet.tiago.application.dto.filter.StudioFilterDTO;
import br.edu.infnet.tiago.domain.model.Studio;
import org.springframework.data.jpa.domain.Specification;

public class StudioSpecification {

    public static Specification<Studio> create(StudioFilterDTO filter) {
        return (root, query, criteriaBuilder) -> {
            Specification<Studio> specification = Specification.where(null);
            return specification.toPredicate(root, query, criteriaBuilder);
        };
    }
}