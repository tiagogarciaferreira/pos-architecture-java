package br.edu.infnet.tiago.application.specification;

import br.edu.infnet.tiago.application.dto.filter.GenreFilterDTO;
import br.edu.infnet.tiago.domain.model.Genre;
import org.springframework.data.jpa.domain.Specification;

public class GenreSpecification {

    public static Specification<Genre> create(GenreFilterDTO filter) {
        return (root, query, criteriaBuilder) -> {
            Specification<Genre> specification = Specification.where(null);
            return specification.toPredicate(root, query, criteriaBuilder);
        };
    }
}