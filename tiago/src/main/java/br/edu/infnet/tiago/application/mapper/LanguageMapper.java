package br.edu.infnet.tiago.application.mapper;

import br.edu.infnet.tiago.application.dto.LanguageCreateDTO;
import br.edu.infnet.tiago.application.dto.LanguageDTO;
import br.edu.infnet.tiago.application.dto.LanguageFullDTO;
import br.edu.infnet.tiago.application.dto.LanguageUpdateDTO;
import br.edu.infnet.tiago.domain.model.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.edu.infnet.tiago.shared.utils.ListUtils.defaultIfNull;

@Component
@RequiredArgsConstructor
public class LanguageMapper {

    private final MapperFactory mapperFactory;

    public LanguageDTO toDTO(Language language) {
        return mapperFactory.mapToNewInstance(language, LanguageDTO.class);
    }

    public LanguageFullDTO toFullDTO(Language language) {
        return mapperFactory.mapToNewInstance(language, LanguageFullDTO.class);
    }

    public List<LanguageDTO> toDTO(List<Language> languages) {
        return defaultIfNull(languages).stream().map(this::toDTO).toList();
    }

    public Language fromDTO(LanguageCreateDTO languageCreateDTO) {
        return mapperFactory.mapToNewInstance(languageCreateDTO, Language.class);
    }

    public Language fromDTO(LanguageUpdateDTO languageUpdateDTO) {
        return mapperFactory.mapToNewInstance(languageUpdateDTO, Language.class);
    }
}