package br.edu.infnet.tiago.domain.specification;

import br.edu.infnet.tiago.application.dto.filter.LanguageFilterDTO;
import br.edu.infnet.tiago.domain.model.Language;
import br.edu.infnet.tiago.shared.utils.ListUtils;
import br.edu.infnet.tiago.shared.utils.StringUtils;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class LanguageSpecification {

    public static Specification<Language> create(LanguageFilterDTO filter) {
        return (root, query, builder) -> {

            var languageFilterDTO = isNull(filter) ? new LanguageFilterDTO() : filter;
            List<Predicate> predicates = new ArrayList<>();
            languageFilterDTO.setIds(ListUtils.getValidValues(languageFilterDTO.getIds()));

            if (!ListUtils.isNullOrEmpty(languageFilterDTO.getIds())) {
                predicates.add(root.get("id").in(languageFilterDTO.getIds()));
            }
            if (!StringUtils.isNullOrEmpty(languageFilterDTO.getName())) {
                predicates.add(builder.like(builder.lower(root.get("name")), languageFilterDTO.getName().toLowerCase() + "%"));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}