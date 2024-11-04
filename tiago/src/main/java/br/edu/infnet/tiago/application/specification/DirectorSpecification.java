package br.edu.infnet.tiago.application.specification;

import br.edu.infnet.tiago.application.dto.filter.DirectorFilterDTO;
import br.edu.infnet.tiago.domain.model.Director;
import org.springframework.data.jpa.domain.Specification;

public class DirectorSpecification {

    public static Specification<Director> create(DirectorFilterDTO filter) {
        return (root, query, criteriaBuilder) -> {
            Specification<Director> specification = Specification.where(null);
            return specification.toPredicate(root, query, criteriaBuilder);
        };
    }
}