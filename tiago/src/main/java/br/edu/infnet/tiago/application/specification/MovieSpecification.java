package br.edu.infnet.tiago.application.specification;

import br.edu.infnet.tiago.application.dto.filter.MovieFilterDTO;
import br.edu.infnet.tiago.domain.model.Movie;
import org.springframework.data.jpa.domain.Specification;

public class MovieSpecification {

    public static Specification<Movie> create(MovieFilterDTO filter) {
        return (root, query, criteriaBuilder) -> {
            Specification<Movie> specification = Specification.where(null);
            return specification.toPredicate(root, query, criteriaBuilder);
        };
    }
}