package br.edu.infnet.tiago.domain.specification;

import br.edu.infnet.tiago.application.dto.filter.DirectorFilterDTO;
import br.edu.infnet.tiago.domain.model.Director;
import br.edu.infnet.tiago.shared.utils.ListUtils;
import br.edu.infnet.tiago.shared.utils.StringUtils;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class DirectorSpecification {

    public static Specification<Director> create(DirectorFilterDTO filter) {
        return (root, query, builder) -> {

            var directorFilterDTO = isNull(filter) ? new DirectorFilterDTO() : filter;
            List<Predicate> predicates = new ArrayList<>();

            directorFilterDTO.setIds(ListUtils.getValidValues(directorFilterDTO.getIds()));
            directorFilterDTO.setCountryIds(ListUtils.getValidValues(directorFilterDTO.getCountryIds()));

            if (!ListUtils.isNullOrEmpty(directorFilterDTO.getIds())) {
                predicates.add(root.get("id").in(directorFilterDTO.getIds()));
            }
            if (!StringUtils.isNullOrEmpty(directorFilterDTO.getName())) {
                predicates.add(builder.like(builder.lower(root.get("name")), "%" + directorFilterDTO.getName().toLowerCase()));
            }
            if (!ListUtils.isNullOrEmpty(directorFilterDTO.getCountryIds())) {
                predicates.add(root.get("country").in(directorFilterDTO.getCountryIds()));
            }
            if (!isNull(directorFilterDTO.getDateOfBirthFrom())) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("dateOfBirth"), directorFilterDTO.getDateOfBirthFrom()));
            }
            if (!isNull(directorFilterDTO.getDateOfBirthTo())) {
                predicates.add(builder.lessThanOrEqualTo(root.get("dateOfBirth"), directorFilterDTO.getDateOfBirthTo()));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}