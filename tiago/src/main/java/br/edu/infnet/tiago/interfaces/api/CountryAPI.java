package br.edu.infnet.tiago.interfaces.api;

import br.edu.infnet.tiago.application.dto.CountryCreateDTO;
import br.edu.infnet.tiago.application.dto.CountryDTO;
import br.edu.infnet.tiago.application.dto.CountryUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.CountryFilterDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface CountryAPI {

    ResponseEntity<CountryDTO> create(@RequestBody CountryCreateDTO countryCreateDTO);

    ResponseEntity<CountryDTO> getById(@PathVariable Long countryId);

    ResponseEntity<CountryDTO> update(@PathVariable Long countryId, @RequestBody CountryUpdateDTO countryUpdateDTO);

    ResponseEntity<Void> delete(@PathVariable Long countryId);

    ResponseEntity<Page<CountryDTO>> search(@ModelAttribute CountryFilterDTO filter,
                                            @RequestParam(defaultValue = "0") @Min(0) int page,
                                            @RequestParam(defaultValue = "10") @Min(10) @Max(100) int size,
                                            @RequestParam(defaultValue = "name,asc") String[] sort);
}