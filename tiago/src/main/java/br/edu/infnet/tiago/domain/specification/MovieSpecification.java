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

            var movieFilterDTO = isNull(filter) ? new MovieFilterDTO() : filter;
            List<Predicate> predicates = new ArrayList<>();

            movieFilterDTO.setIds(ListUtils.getValidValues(movieFilterDTO.getIds()));
            movieFilterDTO.setImdbIds(ListUtils.toLowerCase(movieFilterDTO.getImdbIds()));
            movieFilterDTO.setCountryIds(ListUtils.getValidValues(movieFilterDTO.getCountryIds()));
            movieFilterDTO.setGenreIds(ListUtils.getValidValues(movieFilterDTO.getGenreIds()));
            movieFilterDTO.setStudioIds(ListUtils.getValidValues(movieFilterDTO.getStudioIds()));
            movieFilterDTO.setActorIds(ListUtils.getValidValues(movieFilterDTO.getActorIds()));
            movieFilterDTO.setDirectorIds(ListUtils.getValidValues(movieFilterDTO.getDirectorIds()));
            movieFilterDTO.setLanguageIds(ListUtils.getValidValues(movieFilterDTO.getLanguageIds()));
            movieFilterDTO.setSubtitleIds(ListUtils.getValidValues(movieFilterDTO.getSubtitleIds()));

            if (!ListUtils.isNullOrEmpty(movieFilterDTO.getIds())) {
                predicates.add(root.get("id").in(movieFilterDTO.getIds()));
            }
            if (!StringUtils.isNullOrEmpty(movieFilterDTO.getTitle())) {
                predicates.add(builder.like(builder.lower(root.get("title")), "%" + movieFilterDTO.getTitle().toLowerCase() + "%"));
            }
            if (!ListUtils.isNullOrEmpty(movieFilterDTO.getImdbIds())) {
                predicates.add(builder.in(builder.lower(root.get("imdbId"))).in(movieFilterDTO.getImdbIds()));
            }
            if (!isNull(movieFilterDTO.getReleaseDateFrom())) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("releaseDate"), movieFilterDTO.getReleaseDateFrom()));
            }
            if (!isNull(movieFilterDTO.getReleaseDateTo())) {
                predicates.add(builder.lessThanOrEqualTo(root.get("releaseDate"), movieFilterDTO.getReleaseDateTo()));
            }
            if (!ListUtils.isNullOrEmpty(movieFilterDTO.getCountryIds())) {
                predicates.add(root.get("country").in(movieFilterDTO.getCountryIds()));
            }
            if (!ListUtils.isNullOrEmpty(movieFilterDTO.getGenreIds())) {
                predicates.add(root.get("genre").in(movieFilterDTO.getGenreIds()));
            }
            if (!ListUtils.isNullOrEmpty(movieFilterDTO.getStudioIds())) {
                predicates.add(root.get("studio").in(movieFilterDTO.getStudioIds()));
            }
            if (!ListUtils.isNullOrEmpty(movieFilterDTO.getActorIds())) {
                predicates.add(root.get("actor").in(movieFilterDTO.getActorIds()));
            }
            if (!ListUtils.isNullOrEmpty(movieFilterDTO.getDirectorIds())) {
                predicates.add(root.get("director").in(movieFilterDTO.getDirectorIds()));
            }
            if (!ListUtils.isNullOrEmpty(movieFilterDTO.getLanguageIds())) {
                predicates.add(root.get("language").in(movieFilterDTO.getLanguageIds()));
            }
            if (!ListUtils.isNullOrEmpty(movieFilterDTO.getSubtitleIds())) {
                predicates.add(root.get("subtitle").in(movieFilterDTO.getSubtitleIds()));
            }
            if (movieFilterDTO.getDurationMinutesFrom() > 0) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("durationMinutes"), movieFilterDTO.getDurationMinutesFrom()));
            }
            if (movieFilterDTO.getDurationMinutesTo() > 0) {
                predicates.add(builder.lessThanOrEqualTo(root.get("durationMinutes"), movieFilterDTO.getDurationMinutesTo()));
            }
            if (movieFilterDTO.getBudgetDollarsFrom() > 0) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("budgetDollars"), movieFilterDTO.getBudgetDollarsFrom()));
            }
            if (movieFilterDTO.getBudgetDollarsTo() > 0) {
                predicates.add(builder.lessThanOrEqualTo(root.get("budgetDollars"), movieFilterDTO.getBudgetDollarsTo()));
            }
            if (movieFilterDTO.getBoxOfficeDollarsFrom() > 0) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("boxOfficeDollars"), movieFilterDTO.getBoxOfficeDollarsFrom()));
            }
            if (movieFilterDTO.getBoxOfficeDollarsTo() > 0) {
                predicates.add(builder.lessThanOrEqualTo(root.get("boxOfficeDollars"), movieFilterDTO.getBoxOfficeDollarsTo()));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}