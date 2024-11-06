package br.edu.infnet.tiago.application.specification;

import br.edu.infnet.tiago.application.dto.filter.LanguageFilterDTO;
import br.edu.infnet.tiago.domain.model.Language;
import org.springframework.data.jpa.domain.Specification;

public class LanguageSpecification {

    public static Specification<Language> create(LanguageFilterDTO filter) {
        return (root, query, criteriaBuilder) -> {
            Specification<Language> specification = Specification.where(null);
            return specification.toPredicate(root, query, criteriaBuilder);
        };
    }
}