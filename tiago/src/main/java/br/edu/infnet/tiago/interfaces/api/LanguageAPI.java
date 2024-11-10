package br.edu.infnet.tiago.interfaces.api;

import br.edu.infnet.tiago.application.dto.LanguageCreateDTO;
import br.edu.infnet.tiago.application.dto.LanguageDTO;
import br.edu.infnet.tiago.application.dto.LanguageUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.LanguageFilterDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface LanguageAPI {

    ResponseEntity<LanguageDTO> create(@RequestBody LanguageCreateDTO languageCreateDTO);

    ResponseEntity<LanguageDTO> getById(@PathVariable Long languageId);

    ResponseEntity<LanguageDTO> update(@PathVariable Long languageId, @RequestBody LanguageUpdateDTO languageUpdateDTO);

    ResponseEntity<Void> delete(@PathVariable Long languageId);

    ResponseEntity<Page<LanguageDTO>> search(@ModelAttribute LanguageFilterDTO filter,
                                             @RequestParam(defaultValue = "0") @Min(0) int page,
                                             @RequestParam(defaultValue = "10") @Min(10) @Max(100) int size,
                                             @RequestParam(defaultValue = "name,asc") String[] sort);
}