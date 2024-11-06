package br.edu.infnet.tiago.application.mapper;

import br.edu.infnet.tiago.application.dto.CountryCreateDTO;
import br.edu.infnet.tiago.application.dto.CountryDTO;
import br.edu.infnet.tiago.application.dto.CountryUpdateDTO;
import br.edu.infnet.tiago.domain.model.Country;

import java.util.List;

public class CountryMapper {

    public static CountryDTO toDTO(Country country) {
        return new CountryDTO();
    }

    public static List<CountryDTO> toDTO(List<Country> countries) {
        return countries.stream().map(CountryMapper::toDTO).toList();
    }

    public static Country fromDTO(CountryDTO countryDTO) {
        return new Country();
    }

    public static Country fromDTO(CountryCreateDTO countryCreateDTO) {
        return new Country();
    }

    public static Country fromDTO(CountryUpdateDTO countryUpdateDTO) {
        return new Country();
    }
}