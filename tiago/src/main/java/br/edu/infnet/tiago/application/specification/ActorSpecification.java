package br.edu.infnet.tiago.application.specification;

import br.edu.infnet.tiago.application.dto.filter.ActorFilterDTO;
import br.edu.infnet.tiago.domain.model.Actor;
import org.springframework.data.jpa.domain.Specification;

public class ActorSpecification {

    public static Specification<Actor> create(ActorFilterDTO filter) {
        return (root, query, criteriaBuilder) -> {
            Specification<Actor> specification = Specification.where(null);
            return specification.toPredicate(root, query, criteriaBuilder);
        };
    }
}