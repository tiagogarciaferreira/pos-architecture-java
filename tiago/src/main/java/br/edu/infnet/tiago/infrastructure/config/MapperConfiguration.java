package br.edu.infnet.tiago.infrastructure.config;

import br.edu.infnet.tiago.application.dto.*;
import br.edu.infnet.tiago.domain.model.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static br.edu.infnet.tiago.shared.utils.ListUtils.defaultIfNull;

@SuppressWarnings("unused")
@Configuration
public class MapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {

        final ModelMapper modelMapper = new ModelMapper();
        Converter<Long, Country> countryIdToCountryConverter = context -> new Country().withId(context.getSource());
        Converter<Long, Studio> countryIdToStudioConverter = context -> new Studio().withId(context.getSource());

        Converter<Long, Director> countryIdToDirectorConverter = context -> {
            Director director = new Director();
            director.setId(context.getSource());
            return director;
        };

        Converter<List<Long>, List<Genre>> genreIdsToGenresConverter = context -> defaultIfNull(context.getSource())
                .stream()
                .distinct()
                .map(genreId -> new Genre().withId(genreId))
                .toList();
        Converter<List<Long>, List<Language>> languageIdsToLanguagesConverter = context -> defaultIfNull(context.getSource())
                .stream()
                .distinct()
                .map(languageId -> new Language().withId(languageId))
                .toList();
        Converter<List<Long>, List<Actor>> actorIdsToActorsConverter = context -> defaultIfNull(context.getSource())
                .stream()
                .distinct()
                .map(actorId -> {
                    Actor actor = new Actor();
                    actor.setId(actorId);
                    return actor;
                })
                .toList();

        modelMapper.typeMap(ActorCreateDTO.class, Actor.class).addMappings(mapper ->
                mapper.using(countryIdToCountryConverter).map(ActorCreateDTO::getCountryId, Actor::setCountry)
        );
        modelMapper.typeMap(ActorUpdateDTO.class, Actor.class).addMappings(mapper ->
                mapper.using(countryIdToCountryConverter).map(ActorUpdateDTO::getCountryId, Actor::setCountry)
        );
        modelMapper.typeMap(DirectorCreateDTO.class, Director.class).addMappings(mapper ->
                mapper.using(countryIdToCountryConverter).map(DirectorCreateDTO::getCountryId, Director::setCountry)
        );
        modelMapper.typeMap(DirectorUpdateDTO.class, Director.class).addMappings(mapper ->
                mapper.using(countryIdToCountryConverter).map(DirectorUpdateDTO::getCountryId, Director::setCountry)
        );
        modelMapper.addMappings(new PropertyMap<MovieCreateDTO, Movie>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                using(countryIdToCountryConverter).map(source.getCountryId(), destination.getCountry());
                using(genreIdsToGenresConverter).map(source.getGenreIds(), destination.getGenres());
                using(countryIdToDirectorConverter).map(source.getDirectorId(), destination.getDirector());
                using(countryIdToStudioConverter).map(source.getStudioId(), destination.getStudio());
                using(actorIdsToActorsConverter).map(source.getActorIds(), destination.getActors());
                using(languageIdsToLanguagesConverter).map(source.getLanguageIds(), destination.getLanguages());
                using(languageIdsToLanguagesConverter).map(source.getSubtitleIds(), destination.getSubtitles());
            }
        });
        modelMapper.addMappings(new PropertyMap<MovieUpdateDTO, Movie>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                using(countryIdToCountryConverter).map(source.getCountryId(), destination.getCountry());
                using(genreIdsToGenresConverter).map(source.getGenreIds(), destination.getGenres());
                using(countryIdToDirectorConverter).map(source.getDirectorId(), destination.getDirector());
                using(countryIdToStudioConverter).map(source.getStudioId(), destination.getStudio());
                using(actorIdsToActorsConverter).map(source.getActorIds(), destination.getActors());
                using(languageIdsToLanguagesConverter).map(source.getLanguageIds(), destination.getLanguages());
                using(languageIdsToLanguagesConverter).map(source.getSubtitleIds(), destination.getSubtitles());
            }
        });
        return modelMapper;
    }
}