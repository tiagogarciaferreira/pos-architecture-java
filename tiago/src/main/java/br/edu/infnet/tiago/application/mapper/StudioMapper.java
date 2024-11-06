package br.edu.infnet.tiago.application.mapper;

import br.edu.infnet.tiago.application.dto.StudioCreateDTO;
import br.edu.infnet.tiago.application.dto.StudioDTO;
import br.edu.infnet.tiago.application.dto.StudioUpdateDTO;
import br.edu.infnet.tiago.domain.model.Studio;

import java.util.List;

public class StudioMapper {

    public static StudioDTO toDTO(Studio actor) {
        return new StudioDTO();
    }

    public static List<StudioDTO> toDTO(List<Studio> studios) {
        return studios.stream().map(StudioMapper::toDTO).toList();
    }

    public static Studio fromDTO(StudioDTO studioDTO) {
        return new Studio();
    }

    public static Studio fromDTO(StudioCreateDTO studioCreateDTO) {
        return new Studio();
    }

    public static Studio fromDTO(StudioUpdateDTO studioUpdateDTO) {
        return new Studio();
    }
}