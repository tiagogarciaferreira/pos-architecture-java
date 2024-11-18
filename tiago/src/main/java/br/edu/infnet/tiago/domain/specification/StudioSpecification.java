package br.edu.infnet.tiago.domain.specification;

import br.edu.infnet.tiago.application.dto.filter.StudioFilterDTO;
import br.edu.infnet.tiago.domain.model.Studio;
import br.edu.infnet.tiago.shared.utils.ListUtils;
import br.edu.infnet.tiago.shared.utils.StringUtils;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class StudioSpecification {

    public static Specification<Studio> create(StudioFilterDTO filter) {
        return (root, query, builder) -> {

            var studioFilterDTO = isNull(filter) ? new StudioFilterDTO() : filter;
            List<Predicate> predicates = new ArrayList<>();
            studioFilterDTO.setIds(ListUtils.getValidValues(studioFilterDTO.getIds()));

            if (!ListUtils.isNullOrEmpty(studioFilterDTO.getIds())) {
                predicates.add(root.get("id").in(studioFilterDTO.getIds()));
            }
            if (!StringUtils.isNullOrEmpty(studioFilterDTO.getName())) {
                predicates.add(builder.like(builder.lower(root.get("name")), "%" + studioFilterDTO.getName().toLowerCase()));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}