package br.edu.infnet.tiago.domain.specification;

import br.edu.infnet.tiago.application.dto.filter.GenreFilterDTO;
import br.edu.infnet.tiago.domain.model.Genre;
import br.edu.infnet.tiago.shared.utils.ListUtils;
import br.edu.infnet.tiago.shared.utils.StringUtils;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class GenreSpecification {

    public static Specification<Genre> create(GenreFilterDTO filter) {
        return (root, query, builder) -> {

            var genreFilterDTO = isNull(filter) ? new GenreFilterDTO() : filter;
            List<Predicate> predicates = new ArrayList<>();
            genreFilterDTO.setIds(ListUtils.getValidValues(genreFilterDTO.getIds()));

            if (!ListUtils.isNullOrEmpty(genreFilterDTO.getIds())) {
                predicates.add(root.get("id").in(genreFilterDTO.getIds()));
            }
            if (!StringUtils.isNullOrEmpty(genreFilterDTO.getName())) {
                predicates.add(builder.like(builder.lower(root.get("name")), "%" + genreFilterDTO.getName().toLowerCase()));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}