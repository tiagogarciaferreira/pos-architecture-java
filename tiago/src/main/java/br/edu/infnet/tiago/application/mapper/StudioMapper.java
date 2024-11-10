package br.edu.infnet.tiago.application.mapper;

import br.edu.infnet.tiago.application.dto.StudioCreateDTO;
import br.edu.infnet.tiago.application.dto.StudioDTO;
import br.edu.infnet.tiago.application.dto.StudioUpdateDTO;
import br.edu.infnet.tiago.domain.model.Studio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.edu.infnet.tiago.shared.utils.ListUtils.defaultIfNull;

@Component
@RequiredArgsConstructor
public class StudioMapper {

    private final MapperFactory mapperFactory;

    public StudioDTO toDTO(Studio studio) {
        return mapperFactory.mapToNewInstance(studio, StudioDTO.class);
    }

    public List<StudioDTO> toDTO(List<Studio> studios) {
        return defaultIfNull(studios).stream().map(this::toDTO).toList();
    }

    public Studio fromDTO(StudioCreateDTO studioCreateDTO) {
        return mapperFactory.mapToNewInstance(studioCreateDTO, Studio.class);
    }

    public Studio fromDTO(StudioUpdateDTO studioUpdateDTO) {
        return mapperFactory.mapToNewInstance(studioUpdateDTO, Studio.class);
    }
}