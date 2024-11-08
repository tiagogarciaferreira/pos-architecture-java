package br.edu.infnet.tiago.v2.domain.service;


import br.edu.infnet.tiago.v2.domain.model.Language;
import br.edu.infnet.tiago.v2.domain.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return languageRepository.findById(languageId).get();
    }

    @Transactional
    public Language update(Language language) {
        return languageRepository.save(language);
    }

    @Transactional
    public void delete(Long languageId) {
        languageRepository.deleteById(languageId);
    }
}