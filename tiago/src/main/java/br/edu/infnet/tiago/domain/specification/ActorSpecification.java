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

            var actorFilterDTO = isNull(filter) ? new ActorFilterDTO() : filter;
            List<Predicate> predicates = new ArrayList<>();

            actorFilterDTO.setIds(ListUtils.getValidValues(actorFilterDTO.getIds()));
            actorFilterDTO.setCountryIds(ListUtils.getValidValues(actorFilterDTO.getCountryIds()));

            if (!ListUtils.isNullOrEmpty(actorFilterDTO.getIds())) {
                predicates.add(root.get("id").in(actorFilterDTO.getIds()));
            }
            if (!StringUtils.isNullOrEmpty(actorFilterDTO.getName())) {
                predicates.add(builder.like(builder.lower(root.get("name")), actorFilterDTO.getName().toLowerCase() + "%"));
            }
            if (!ListUtils.isNullOrEmpty(actorFilterDTO.getCountryIds())) {
                predicates.add(root.get("country").in(actorFilterDTO.getCountryIds()));
            }
            if (!isNull(actorFilterDTO.getDateOfBirthFrom())) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("dateOfBirth"), actorFilterDTO.getDateOfBirthFrom()));
            }
            if (!isNull(actorFilterDTO.getDateOfBirthTo())) {
                predicates.add(builder.lessThanOrEqualTo(root.get("dateOfBirth"), actorFilterDTO.getDateOfBirthTo()));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}