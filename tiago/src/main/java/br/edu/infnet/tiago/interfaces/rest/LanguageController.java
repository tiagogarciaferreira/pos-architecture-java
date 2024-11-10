package br.edu.infnet.tiago.interfaces.rest;

import br.edu.infnet.tiago.application.dto.LanguageCreateDTO;
import br.edu.infnet.tiago.application.dto.LanguageDTO;
import br.edu.infnet.tiago.application.dto.LanguageUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.LanguageFilterDTO;
import br.edu.infnet.tiago.application.mapper.LanguageMapper;
import br.edu.infnet.tiago.application.utility.SortParameterConverter;
import br.edu.infnet.tiago.domain.model.Language;
import br.edu.infnet.tiago.domain.service.LanguageService;
import br.edu.infnet.tiago.domain.specification.LanguageSpecification;
import br.edu.infnet.tiago.interfaces.api.LanguageAPI;
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

@Tag(name = "Languages", description = "Language management")
@RestController
@RequestMapping("/v1/languages")
@RequiredArgsConstructor
@Slf4j
public class LanguageController implements LanguageAPI {

    private final LanguageMapper languageMapper;

    private final LanguageService languageService;

    @Override
    @PostMapping
    public ResponseEntity<LanguageDTO> create(@RequestBody LanguageCreateDTO languageCreateDTO) {
        Language language = languageMapper.fromDTO(languageCreateDTO);
        language = languageService.create(language);
        LanguageDTO languageDTO = languageMapper.toDTO(language);
        log.info("Language '{}', was successfully created", language.getId());
        return new ResponseEntity<>(languageDTO, CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<LanguageDTO> getById(@PathVariable Long languageId) {
        Language language = languageService.getById(languageId);
        LanguageDTO languageDTO = languageMapper.toDTO(language);
        log.info("Language '{}', was successfully retrieved", languageId);
        return new ResponseEntity<>(languageDTO, OK);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<LanguageDTO> update(@PathVariable Long languageId, @RequestBody LanguageUpdateDTO languageUpdateDTO) {
        Language language = languageMapper.fromDTO(languageUpdateDTO);
        language = languageService.update(language);
        LanguageDTO languageDTO = languageMapper.toDTO(language);
        log.info("Language '{}', was successfully updated", languageId);
        return new ResponseEntity<>(languageDTO, OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long languageId) {
        languageService.delete(languageId);
        log.info("Language '{}', was successfully removed", languageId);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<LanguageDTO>> search(@ModelAttribute LanguageFilterDTO filter,
                                                    @RequestParam(defaultValue = "0") @Min(0) int page,
                                                    @RequestParam(defaultValue = "10") @Min(10) @Max(100) int size,
                                                    @RequestParam(defaultValue = "name,asc") String[] sort) {

        Specification<Language> specification = LanguageSpecification.create(filter);
        Pageable pageable = PageRequest.of(page, size, SortParameterConverter.toSort(sort));
        Page<Language> languagePage = languageService.search(specification, pageable);
        List<LanguageDTO> languages = languageMapper.toDTO(languagePage.getContent());
        Page<LanguageDTO> dtoPage = new PageImpl<>(languages, pageable, languagePage.getTotalElements());
        log.info("'{}' languages were successfully listed", languages.size());
        return new ResponseEntity<>(dtoPage, OK);
    }
}