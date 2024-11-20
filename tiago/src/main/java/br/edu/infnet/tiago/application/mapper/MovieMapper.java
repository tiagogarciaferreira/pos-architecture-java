package br.edu.infnet.tiago.application.mapper;

import br.edu.infnet.tiago.application.dto.MovieCreateDTO;
import br.edu.infnet.tiago.application.dto.MovieDTO;
import br.edu.infnet.tiago.application.dto.MovieFullDTO;
import br.edu.infnet.tiago.application.dto.MovieUpdateDTO;
import br.edu.infnet.tiago.domain.model.Actor;
import br.edu.infnet.tiago.domain.model.Genre;
import br.edu.infnet.tiago.domain.model.Language;
import br.edu.infnet.tiago.domain.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

import static br.edu.infnet.tiago.shared.utils.ListUtils.defaultIfNull;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDTO toDTO(Movie movie);

    MovieFullDTO toFullDTO(Movie movie);

    List<MovieDTO> toDTO(List<Movie> movies);

    @Mapping(source = "directorId", target = "director.id")
    @Mapping(source = "studioId", target = "studio.id")
    @Mapping(source = "countryId", target = "country.id")
    @Mapping(source = "genreIds", target = "genres", qualifiedByName = {"genreIdsToGenres"})
    @Mapping(source = "languageIds", target = "languages", qualifiedByName = {"languageIdsToLanguages"})
    @Mapping(source = "subtitleIds", target = "subtitles", qualifiedByName = {"subtitleIdsToLanguages"})
    @Mapping(source = "actorIds", target = "actors", qualifiedByName = {"actorIdsToActors"})
    Movie fromDTO(MovieCreateDTO movieCreateDTO);

    @Mapping(source = "directorId", target = "director.id")
    @Mapping(source = "studioId", target = "studio.id")
    @Mapping(source = "countryId", target = "country.id")
    @Mapping(source = "genreIds", target = "genres", qualifiedByName = {"genreIdsToGenres"})
    @Mapping(source = "languageIds", target = "languages", qualifiedByName = {"languageIdsToLanguages"})
    @Mapping(source = "subtitleIds", target = "subtitles", qualifiedByName = {"subtitleIdsToLanguages"})
    @Mapping(source = "actorIds", target = "actors", qualifiedByName = {"actorIdsToActors"})
    Movie fromDTO(MovieUpdateDTO movieUpdateDTO);

    @Named("genreIdsToGenres")
    default List<Genre> genreIdsToGenres(List<Long> genreIds) {
        return defaultIfNull(genreIds).stream().map(genreId -> new Genre().withId(genreId)).toList();
    }

    @Named("languageIdsToLanguages")
    default List<Language> languageIdsToLanguages(List<Long> languageIds) {
        return defaultIfNull(languageIds).stream().map(languageId -> new Language().withId(languageId)).toList();
    }

    @Named("subtitleIdsToLanguages")
    default List<Language> subtitleIdsToLanguages(List<Long> subtitleIds) {
        return defaultIfNull(subtitleIds).stream().map(languageId -> new Language().withId(languageId)).toList();
    }

    @Named("actorIdsToActors")
    default List<Actor> actorIdsToActors(List<Long> actorIds) {
        return defaultIfNull(actorIds).stream().map(actorId -> {
            Actor actor = new Actor();
            actor.setId(actorId);
            return actor;
        }).toList();
    }
}