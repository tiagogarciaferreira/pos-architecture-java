package br.edu.infnet.tiago.domain.specification;

import br.edu.infnet.tiago.application.dto.filter.LanguageFilterDTO;
import br.edu.infnet.tiago.domain.model.Language;
import br.edu.infnet.tiago.shared.utils.ListUtils;
import br.edu.infnet.tiago.shared.utils.StringUtils;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class LanguageSpecification {

    public static Specification<Language> create(LanguageFilterDTO filter) {
        return (root, query, builder) -> {

            List<Predicate> predicates = new ArrayList<>();
            filter.setIds(ListUtils.getValidValues(filter.getIds()));

            if (!ListUtils.isNullOrEmpty(filter.getIds())) {
                predicates.add(root.get("id").in(filter.getIds()));
            }
            if (!StringUtils.isNullOrEmpty(filter.getName())) {
                predicates.add(builder.like(builder.lower(root.get("name")), "%" + filter.getName().toLowerCase()));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}