package br.edu.infnet.tiago.domain.service;


import br.edu.infnet.tiago.domain.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
}
