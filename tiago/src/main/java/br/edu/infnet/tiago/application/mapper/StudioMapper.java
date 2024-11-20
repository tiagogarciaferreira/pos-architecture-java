package br.edu.infnet.tiago.application.mapper;

import br.edu.infnet.tiago.application.dto.StudioCreateDTO;
import br.edu.infnet.tiago.application.dto.StudioDTO;
import br.edu.infnet.tiago.application.dto.StudioFullDTO;
import br.edu.infnet.tiago.application.dto.StudioUpdateDTO;
import br.edu.infnet.tiago.domain.model.Studio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudioMapper {

    StudioDTO toDTO(Studio studio);

    StudioFullDTO toFullDTO(Studio studio);

    List<StudioDTO> toDTO(List<Studio> studios);

    @Mapping(source = "countryId", target = "country.id")
    Studio fromDTO(StudioCreateDTO studioCreateDTO);

    @Mapping(source = "countryId", target = "country.id")
    Studio fromDTO(StudioUpdateDTO studioUpdateDTO);
}