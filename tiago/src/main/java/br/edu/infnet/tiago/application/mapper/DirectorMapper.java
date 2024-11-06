package br.edu.infnet.tiago.application.mapper;

import br.edu.infnet.tiago.application.dto.DirectorCreateDTO;
import br.edu.infnet.tiago.application.dto.DirectorDTO;
import br.edu.infnet.tiago.application.dto.DirectorUpdateDTO;
import br.edu.infnet.tiago.domain.model.Director;

import java.util.List;

public class DirectorMapper {

    public static DirectorDTO toDTO(Director director) {
        return new DirectorDTO();
    }

    public static List<DirectorDTO> toDTO(List<Director> directors) {
        return directors.stream().map(DirectorMapper::toDTO).toList();
    }

    public static Director fromDTO(DirectorDTO directorDTO) {
        return new Director();
    }

    public static Director fromDTO(DirectorCreateDTO directorCreateDTO) {
        return new Director();
    }

    public static Director fromDTO(DirectorUpdateDTO directorUpdateDTO) {
        return new Director();
    }
}