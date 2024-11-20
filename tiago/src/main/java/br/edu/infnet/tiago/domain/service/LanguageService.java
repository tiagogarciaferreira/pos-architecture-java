package br.edu.infnet.tiago.domain.service;


import br.edu.infnet.tiago.domain.model.Language;
import br.edu.infnet.tiago.domain.repository.LanguageRepository;
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
public class LanguageService {

    private final LanguageRepository languageRepository;

    @Transactional
    public Language create(Language language) {
        return languageRepository.save(language);
    }

    @Transactional(readOnly = true)
    public Page<Language> search(Specification<Language> specification, Pageable pageable) {
        return languageRepository.findAll(specification, pageable);
    }

    @Transactional(readOnly = true)
    public Language getById(Long languageId) {
        return languageRepository.findById(languageId)
                .orElseThrow(() -> new NotFoundException(format("Language '%s' not found", languageId)));
    }

    @Transactional
    public Language update(Long languageId, Language language) {
        var existingLanguage = getById(languageId).withName(language.getName()).withVersion(language.getVersion());
        return languageRepository.save(existingLanguage);
    }

    @Transactional
    public void delete(Long languageId) {
        if (!exists(languageId)) throw new NotFoundException(format("Language '%s' not found", languageId));
        languageRepository.deleteById(languageId);
    }

    @Transactional(readOnly = true)
    public boolean exists(Long languageId) {
        return languageRepository.existsById(languageId);
    }
}