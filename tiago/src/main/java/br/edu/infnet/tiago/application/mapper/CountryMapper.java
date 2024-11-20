package br.edu.infnet.tiago.application.mapper;

import br.edu.infnet.tiago.application.dto.CountryCreateDTO;
import br.edu.infnet.tiago.application.dto.CountryDTO;
import br.edu.infnet.tiago.application.dto.CountryFullDTO;
import br.edu.infnet.tiago.application.dto.CountryUpdateDTO;
import br.edu.infnet.tiago.domain.model.Country;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;

import java.util.List;

import static br.edu.infnet.tiago.shared.utils.ListUtils.modifyItems;
import static java.util.Objects.isNull;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryDTO toDTO(Country country);

    CountryFullDTO toFullDTO(Country country);

    List<CountryDTO> toDTO(List<Country> countries);

    Country fromDTO(CountryCreateDTO countryCreateDTO);

    Country fromDTO(CountryUpdateDTO countryUpdateDTO);

    @BeforeMapping
    default void beforeMapping(Country country) {
        if (!isNull(country)) {
            country.setMovies(modifyItems(country.getMovies(), movie -> movie.withCountry(null)));
            country.setActors(modifyItems(country.getActors(), actor -> {
                actor.setCountry(null);
                return actor;
            }));
            country.setDirectors(modifyItems(country.getDirectors(), director -> {
                director.setCountry(null);
                return director;
            }));
            country.setStudios(modifyItems(country.getStudios(), studio -> studio.withCountry(null)));
        }
    }
}