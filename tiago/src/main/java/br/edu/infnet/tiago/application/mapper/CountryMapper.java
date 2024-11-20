package br.edu.infnet.tiago.application.mapper;

import br.edu.infnet.tiago.application.dto.*;
import br.edu.infnet.tiago.domain.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static br.edu.infnet.tiago.shared.utils.ListUtils.defaultIfNull;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    MovieMapper MOVIE_MAPPER = Mappers.getMapper(MovieMapper.class);
    DirectorMapper DIRECTOR_MAPPER = Mappers.getMapper(DirectorMapper.class);
    ActorMapper ACTOR_MAPPER = Mappers.getMapper(ActorMapper.class);
    StudioMapper STUDIO_MAPPER = Mappers.getMapper(StudioMapper.class);

    CountryDTO toDTO(Country country);

    @Mapping(source = "movies", target = "movies", qualifiedByName = "ignoreCountryInMovie")
    @Mapping(source = "actors", target = "actors", qualifiedByName = "ignoreCountryInActor")
    @Mapping(source = "directors", target = "directors", qualifiedByName = "ignoreCountryInDirector")
    @Mapping(source = "studios", target = "studios", qualifiedByName = "ignoreCountryInStudio")
    CountryFullDTO toFullDTO(Country country);

    @Mapping(target = "movies", ignore = true)
    List<CountryDTO> toDTO(List<Country> countries);

    Country fromDTO(CountryCreateDTO countryCreateDTO);

    Country fromDTO(CountryUpdateDTO countryUpdateDTO);

    @Named("ignoreCountryInMovie")
    default List<MovieDTO> ignoreCountryInMovie(List<Movie> movies) {
        return defaultIfNull(movies).stream().map(movie -> MOVIE_MAPPER.toDTO(movie.withCountry(null))).toList();
    }

    @Named("ignoreCountryInActor")
    default List<ActorDTO> ignoreCountryInActor(List<Actor> actors) {
        return defaultIfNull(actors).stream()
                .map(actor -> {
                    actor.setCountry(null);
                    return ACTOR_MAPPER.toDTO(actor);
                }).toList();
    }

    @Named("ignoreCountryInDirector")
    default List<DirectorDTO> ignoreCountryInDirector(List<Director> directors) {
        return defaultIfNull(directors).stream()
                .map(director -> {
                    director.setCountry(null);
                    return DIRECTOR_MAPPER.toDTO(director);
                })
                .toList();
    }

    @Named("ignoreCountryInStudio")
    default List<StudioDTO> ignoreCountryInStudio(List<Studio> studios) {
        return defaultIfNull(studios).stream().map(studio -> STUDIO_MAPPER.toDTO(studio.withCountry(null))).toList();
    }
}