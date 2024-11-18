package br.edu.infnet.tiago.domain.specification;

import br.edu.infnet.tiago.application.dto.filter.CountryFilterDTO;
import br.edu.infnet.tiago.domain.model.Country;
import br.edu.infnet.tiago.shared.utils.ListUtils;
import br.edu.infnet.tiago.shared.utils.StringUtils;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class CountrySpecification {

    public static Specification<Country> create(CountryFilterDTO filter) {
        return (root, query, builder) -> {

            var countryFilterDTO = isNull(filter) ? new CountryFilterDTO() : filter;
            List<Predicate> predicates = new ArrayList<>();

            countryFilterDTO.setIds(ListUtils.getValidValues(countryFilterDTO.getIds()));
            countryFilterDTO.setCodes(ListUtils.toLowerCase(countryFilterDTO.getCodes()));

            if (!ListUtils.isNullOrEmpty(countryFilterDTO.getIds())) {
                predicates.add(root.get("id").in(countryFilterDTO.getIds()));
            }
            if (!StringUtils.isNullOrEmpty(countryFilterDTO.getName())) {
                predicates.add(builder.like(builder.lower(root.get("name")), "%" + countryFilterDTO.getName().toLowerCase()));
            }
            if (!ListUtils.isNullOrEmpty(countryFilterDTO.getCodes())) {
                predicates.add(builder.in(builder.lower(root.get("code"))).in(countryFilterDTO.getCodes()));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}