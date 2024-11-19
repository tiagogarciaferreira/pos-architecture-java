package br.edu.infnet.tiago.interfaces.rest;

import br.edu.infnet.tiago.application.dto.CountryCreateDTO;
import br.edu.infnet.tiago.application.dto.CountryDTO;
import br.edu.infnet.tiago.application.dto.CountryFullDTO;
import br.edu.infnet.tiago.application.dto.CountryUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.CountryFilterDTO;
import br.edu.infnet.tiago.application.mapper.CountryMapper;
import br.edu.infnet.tiago.application.utility.SortParameterConverter;
import br.edu.infnet.tiago.domain.model.Country;
import br.edu.infnet.tiago.domain.service.CountryService;
import br.edu.infnet.tiago.domain.specification.CountrySpecification;
import br.edu.infnet.tiago.interfaces.api.CountryAPI;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "Countries", description = "Country management")
@RestController
@RequestMapping("/v1/countries")
@RequiredArgsConstructor
@Slf4j
public class CountryController implements CountryAPI {

    private final CountryMapper countryMapper;

    private final CountryService countryService;

    @Override
    @PostMapping
    public ResponseEntity<CountryDTO> create(@RequestBody CountryCreateDTO countryCreateDTO) {
        Country country = countryMapper.fromDTO(countryCreateDTO);
        country = countryService.create(country);
        CountryDTO countryDTO = countryMapper.toDTO(country);
        log.info("Country '{}', was successfully created", country.getId());
        return new ResponseEntity<>(countryDTO, CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CountryFullDTO> getById(@PathVariable Long id) {
        Country country = countryService.getById(id);
        CountryFullDTO countryFullDTO = countryMapper.toFullDTO(country);
        log.info("Country '{}', was successfully retrieved", id);
        return new ResponseEntity<>(countryFullDTO, OK);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<CountryDTO> update(@PathVariable Long id, @RequestBody CountryUpdateDTO countryUpdateDTO) {
        Country country = countryMapper.fromDTO(countryUpdateDTO);
        country = countryService.update(id, country);
        CountryDTO countryDTO = countryMapper.toDTO(country);
        log.info("Country '{}', was successfully updated", id);
        return new ResponseEntity<>(countryDTO, OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        countryService.delete(id);
        log.info("Country '{}', was successfully removed", id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<CountryDTO>> search(@ModelAttribute CountryFilterDTO filter,
                                                   @RequestParam(defaultValue = "0") @Min(0) int page,
                                                   @RequestParam(defaultValue = "10") @Min(10) @Max(100) int size,
                                                   @RequestParam(defaultValue = "name,asc") String[] sort) {

        Specification<Country> specification = CountrySpecification.create(filter);
        Pageable pageable = PageRequest.of(page, size, SortParameterConverter.toSort(sort));
        Page<Country> countryPage = countryService.search(specification, pageable);
        List<CountryDTO> countries = countryMapper.toDTO(countryPage.getContent());
        Page<CountryDTO> dtoPage = new PageImpl<>(countries, pageable, countryPage.getTotalElements());
        log.info("'{}' countries were successfully listed", countries.size());
        return new ResponseEntity<>(dtoPage, OK);
    }
}