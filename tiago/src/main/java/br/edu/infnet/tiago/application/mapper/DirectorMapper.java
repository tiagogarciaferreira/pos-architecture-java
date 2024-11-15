package br.edu.infnet.tiago.application.mapper;

import br.edu.infnet.tiago.application.dto.DirectorCreateDTO;
import br.edu.infnet.tiago.application.dto.DirectorDTO;
import br.edu.infnet.tiago.application.dto.DirectorFullDTO;
import br.edu.infnet.tiago.application.dto.DirectorUpdateDTO;
import br.edu.infnet.tiago.domain.model.Director;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.edu.infnet.tiago.shared.utils.ListUtils.defaultIfNull;

@Component
@RequiredArgsConstructor
public class DirectorMapper {

    private final MapperFactory mapperFactory;

    public DirectorDTO toDTO(Director director) {
        return mapperFactory.mapToNewInstance(director, DirectorDTO.class);
    }

    public DirectorFullDTO toFullDTO(Director director) {
        return mapperFactory.mapToNewInstance(director, DirectorFullDTO.class);
    }

    public List<DirectorDTO> toDTO(List<Director> directors) {
        return defaultIfNull(directors).stream().map(this::toDTO).toList();
    }

    public Director fromDTO(DirectorCreateDTO directorCreateDTO) {
        return mapperFactory.mapToNewInstance(directorCreateDTO, Director.class);
    }

    public Director fromDTO(DirectorUpdateDTO directorUpdateDTO) {
        return mapperFactory.mapToNewInstance(directorUpdateDTO, Director.class);
    }

}