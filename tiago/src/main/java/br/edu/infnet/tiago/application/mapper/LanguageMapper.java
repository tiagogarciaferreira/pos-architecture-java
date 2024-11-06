package br.edu.infnet.tiago.application.mapper;

import br.edu.infnet.tiago.application.dto.LanguageCreateDTO;
import br.edu.infnet.tiago.application.dto.LanguageDTO;
import br.edu.infnet.tiago.application.dto.LanguageUpdateDTO;
import br.edu.infnet.tiago.domain.model.Language;

import java.util.List;

public class LanguageMapper {

    public static LanguageDTO toDTO(Language language) {
        return new LanguageDTO();
    }

    public static List<LanguageDTO> toDTO(List<Language> languages) {
        return languages.stream().map(LanguageMapper::toDTO).toList();
    }

    public static Language fromDTO(LanguageDTO languageDTO) {
        return new Language();
    }

    public static Language fromDTO(LanguageCreateDTO languageCreateDTO) {
        return new Language();
    }

    public static Language fromDTO(LanguageUpdateDTO languageUpdateDTO) {
        return new Language();
    }
}