package br.edu.infnet.tiago.application.mapper;

import br.edu.infnet.tiago.application.dto.CountryCreateDTO;
import br.edu.infnet.tiago.application.dto.CountryDTO;
import br.edu.infnet.tiago.application.dto.CountryUpdateDTO;
import br.edu.infnet.tiago.domain.model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.edu.infnet.tiago.shared.utils.ListUtils.defaultIfNull;

@Component
@RequiredArgsConstructor
public class CountryMapper {

    private final MapperFactory mapperFactory;

    public CountryDTO toDTO(Country country) {
        return mapperFactory.mapToNewInstance(country, CountryDTO.class);
    }

    public List<CountryDTO> toDTO(List<Country> countries) {
        return defaultIfNull(countries).stream().map(this::toDTO).toList();
    }

    public Country fromDTO(CountryCreateDTO countryCreateDTO) {
        return mapperFactory.mapToNewInstance(countryCreateDTO, Country.class);
    }

    public Country fromDTO(CountryUpdateDTO countryUpdateDTO) {
        return mapperFactory.mapToNewInstance(countryUpdateDTO, Country.class);
    }
}