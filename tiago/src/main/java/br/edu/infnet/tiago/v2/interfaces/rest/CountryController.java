package br.edu.infnet.tiago.v2.interfaces.rest;

import br.edu.infnet.tiago.application.converter.SortConverter;
import br.edu.infnet.tiago.application.dto.CountryCreateDTO;
import br.edu.infnet.tiago.application.dto.CountryDTO;
import br.edu.infnet.tiago.application.dto.CountryUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.CountryFilterDTO;
import br.edu.infnet.tiago.application.mapper.CountryMapper;
import br.edu.infnet.tiago.application.specification.CountrySpecification;
import br.edu.infnet.tiago.domain.model.Country;
import br.edu.infnet.tiago.domain.service.CountryService;
import br.edu.infnet.tiago.v2.interfaces.api.CountryAPI;
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

@RestController
@RequestMapping("/v1/countries")
@RequiredArgsConstructor
@Slf4j
public class CountryController implements CountryAPI {

    private final CountryService countryService;

    @Override
    @PostMapping
    public ResponseEntity<CountryDTO> create(@RequestBody CountryCreateDTO countryCreateDTO) {
        Country country = CountryMapper.fromDTO(countryCreateDTO);
        country = countryService.create(country);
        CountryDTO countryDTO = CountryMapper.toDTO(country);
        log.info("Country '{}', was successfully created", country.getId());
        return new ResponseEntity<>(countryDTO, CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> getById(@PathVariable Long countryId) {
        Country country = countryService.getById(countryId);
        CountryDTO countryDTO = CountryMapper.toDTO(country);
        log.info("Country '{}', was successfully retrieved", countryId);
        return new ResponseEntity<>(countryDTO, OK);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<CountryDTO> update(@PathVariable Long countryId, @RequestBody CountryUpdateDTO countryUpdateDTO) {
        Country country = CountryMapper.fromDTO(countryUpdateDTO);
        country = countryService.update(country);
        CountryDTO countryDTO = CountryMapper.toDTO(country);
        log.info("Country '{}', was successfully updated", countryId);
        return new ResponseEntity<>(countryDTO, OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long countryId) {
        countryService.delete(countryId);
        log.info("Country '{}', was successfully removed", countryId);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<CountryDTO>> search(@ModelAttribute CountryFilterDTO filter,
                                                   @RequestParam(defaultValue = "0") @Min(0) int page,
                                                   @RequestParam(defaultValue = "10") @Min(10) @Max(100) int size,
                                                   @RequestParam(defaultValue = "name,asc") String[] sort) {

        Specification<Country> specification = CountrySpecification.create(filter);
        Pageable pageable = PageRequest.of(page, size, SortConverter.toSort(sort));
        Page<Country> countryPage = countryService.search(specification, pageable);
        List<CountryDTO> countries = CountryMapper.toDTO(countryPage.getContent());
        Page<CountryDTO> dtoPage = new PageImpl<>(countries, pageable, countryPage.getTotalElements());
        log.info("'{}' countries were successfully listed", countries.size());
        return new ResponseEntity<>(dtoPage, OK);
    }
}