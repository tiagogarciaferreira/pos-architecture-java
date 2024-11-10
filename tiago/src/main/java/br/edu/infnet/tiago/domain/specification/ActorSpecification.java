package br.edu.infnet.tiago.domain.specification;

import br.edu.infnet.tiago.application.dto.filter.ActorFilterDTO;
import br.edu.infnet.tiago.domain.model.Actor;
import br.edu.infnet.tiago.shared.utils.ListUtils;
import br.edu.infnet.tiago.shared.utils.StringUtils;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class ActorSpecification {

    public static Specification<Actor> create(ActorFilterDTO filter) {
        return (root, query, builder) -> {

            List<Predicate> predicates = new ArrayList<>();
            filter.setIds(ListUtils.getValidValues(filter.getIds()));
            filter.setCountryIds(ListUtils.getValidValues(filter.getCountryIds()));

            if (!ListUtils.isNullOrEmpty(filter.getIds())) {
                predicates.add(root.get("id").in(filter.getIds()));
            }
            if (!StringUtils.isNullOrEmpty(filter.getName())) {
                predicates.add(builder.like(builder.lower(root.get("name")), "%" + filter.getName().toLowerCase()));
            }
            if (!ListUtils.isNullOrEmpty(filter.getCountryIds())) {
                predicates.add(root.get("country").in(filter.getCountryIds()));
            }
            if (!isNull(filter.getDateOfBirthFrom())) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("dateOfBirth"), filter.getDateOfBirthFrom()));
            }
            if (!isNull(filter.getDateOfBirthTo())) {
                predicates.add(builder.lessThanOrEqualTo(root.get("dateOfBirth"), filter.getDateOfBirthTo()));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}