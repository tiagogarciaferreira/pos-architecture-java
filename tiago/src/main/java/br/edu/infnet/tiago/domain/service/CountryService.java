package br.edu.infnet.tiago.domain.service;


import br.edu.infnet.tiago.domain.model.Country;
import br.edu.infnet.tiago.domain.repository.CountryRepository;
import br.edu.infnet.tiago.infrastructure.exception.custom.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    @Transactional
    public Country create(Country country) {
        return countryRepository.save(country);
    }

    @Transactional(readOnly = true)
    public Page<Country> search(Specification<Country> specification, Pageable pageable) {
        return countryRepository.findAll(specification, pageable);
    }

    @Transactional(readOnly = true)
    public Country getById(Long countryId) {
        return countryRepository.findById(countryId)
                .orElseThrow(() -> new NotFoundException(format("Country '%s' not found", countryId)));
    }

    @Transactional
    public Country update(Country country) {
        return countryRepository.save(country);
    }

    @Transactional
    public void delete(Long countryId) {
        countryRepository.deleteById(countryId);
    }
}