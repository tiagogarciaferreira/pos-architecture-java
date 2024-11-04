package br.edu.infnet.tiago.application.mapper;

import br.edu.infnet.tiago.application.dto.DirectorCreateDTO;
import br.edu.infnet.tiago.application.dto.DirectorDTO;
import br.edu.infnet.tiago.application.dto.DirectorUpdateDTO;
import br.edu.infnet.tiago.domain.model.Director;

public class DirectorMapper {

    public static DirectorDTO toDTO(Director director) {
        return new DirectorDTO();
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