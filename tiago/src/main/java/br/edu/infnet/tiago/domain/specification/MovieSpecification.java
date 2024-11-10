package br.edu.infnet.tiago.domain.specification;

import br.edu.infnet.tiago.application.dto.filter.MovieFilterDTO;
import br.edu.infnet.tiago.domain.model.Movie;
import br.edu.infnet.tiago.shared.utils.ListUtils;
import br.edu.infnet.tiago.shared.utils.StringUtils;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class MovieSpecification {

    public static Specification<Movie> create(MovieFilterDTO filter) {
        return (root, query, builder) -> {

            List<Predicate> predicates = new ArrayList<>();
            filter.setIds(ListUtils.getValidValues(filter.getIds()));
            filter.setImdbIds(ListUtils.toLowerCase(filter.getImdbIds()));
            filter.setCountryIds(ListUtils.getValidValues(filter.getCountryIds()));
            filter.setGenreIds(ListUtils.getValidValues(filter.getGenreIds()));
            filter.setStudioIds(ListUtils.getValidValues(filter.getStudioIds()));
            filter.setActorIds(ListUtils.getValidValues(filter.getActorIds()));
            filter.setDirectorIds(ListUtils.getValidValues(filter.getDirectorIds()));
            filter.setLanguageIds(ListUtils.getValidValues(filter.getLanguageIds()));
            filter.setSubtitleIds(ListUtils.getValidValues(filter.getSubtitleIds()));

            if (!ListUtils.isNullOrEmpty(filter.getIds())) {
                predicates.add(root.get("id").in(filter.getIds()));
            }
            if (!StringUtils.isNullOrEmpty(filter.getTitle())) {
                predicates.add(builder.like(builder.lower(root.get("title")), "%" + filter.getTitle().toLowerCase() + "%"));
            }
            if (!ListUtils.isNullOrEmpty(filter.getImdbIds())) {
                predicates.add(builder.in(builder.lower(root.get("imdbId"))).in(filter.getImdbIds()));
            }
            if (!isNull(filter.getReleaseDateFrom())) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("releaseDate"), filter.getReleaseDateFrom()));
            }
            if (!isNull(filter.getReleaseDateTo())) {
                predicates.add(builder.lessThanOrEqualTo(root.get("releaseDate"), filter.getReleaseDateTo()));
            }
            if (!ListUtils.isNullOrEmpty(filter.getCountryIds())) {
                predicates.add(root.get("country").in(filter.getCountryIds()));
            }
            if (!ListUtils.isNullOrEmpty(filter.getGenreIds())) {
                predicates.add(root.get("genre").in(filter.getGenreIds()));
            }
            if (!ListUtils.isNullOrEmpty(filter.getStudioIds())) {
                predicates.add(root.get("studio").in(filter.getStudioIds()));
            }
            if (!ListUtils.isNullOrEmpty(filter.getActorIds())) {
                predicates.add(root.get("actor").in(filter.getActorIds()));
            }
            if (!ListUtils.isNullOrEmpty(filter.getDirectorIds())) {
                predicates.add(root.get("director").in(filter.getDirectorIds()));
            }
            if (!ListUtils.isNullOrEmpty(filter.getLanguageIds())) {
                predicates.add(root.get("language").in(filter.getLanguageIds()));
            }
            if (!ListUtils.isNullOrEmpty(filter.getSubtitleIds())) {
                predicates.add(root.get("subtitle").in(filter.getSubtitleIds()));
            }
            if (filter.getDurationMinutesFrom() > 0) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("durationMinutes"), filter.getDurationMinutesFrom()));
            }
            if (filter.getDurationMinutesTo() > 0) {
                predicates.add(builder.lessThanOrEqualTo(root.get("durationMinutes"), filter.getDurationMinutesTo()));
            }
            if (filter.getBudgetDollarsFrom() > 0) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("budgetDollars"), filter.getBudgetDollarsFrom()));
            }
            if (filter.getBudgetDollarsTo() > 0) {
                predicates.add(builder.lessThanOrEqualTo(root.get("budgetDollars"), filter.getBudgetDollarsTo()));
            }
            if (filter.getBoxOfficeDollarsFrom() > 0) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("boxOfficeDollars"), filter.getBoxOfficeDollarsFrom()));
            }
            if (filter.getBoxOfficeDollarsTo() > 0) {
                predicates.add(builder.lessThanOrEqualTo(root.get("boxOfficeDollars"), filter.getBoxOfficeDollarsTo()));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}