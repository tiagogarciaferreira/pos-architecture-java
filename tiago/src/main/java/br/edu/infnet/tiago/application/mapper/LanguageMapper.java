package br.edu.infnet.tiago.application.mapper;

import br.edu.infnet.tiago.application.dto.LanguageCreateDTO;
import br.edu.infnet.tiago.application.dto.LanguageDTO;
import br.edu.infnet.tiago.application.dto.LanguageFullDTO;
import br.edu.infnet.tiago.application.dto.LanguageUpdateDTO;
import br.edu.infnet.tiago.domain.model.Language;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LanguageMapper {

    LanguageDTO toDTO(Language language);

    LanguageFullDTO toFullDTO(Language language);

    List<LanguageDTO> toDTO(List<Language> languages);

    Language fromDTO(LanguageCreateDTO languageCreateDTO);

    Language fromDTO(LanguageUpdateDTO languageUpdateDTO);
}