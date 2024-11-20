package br.edu.infnet.tiago.application.mapper;

import br.edu.infnet.tiago.application.dto.DirectorCreateDTO;
import br.edu.infnet.tiago.application.dto.DirectorDTO;
import br.edu.infnet.tiago.application.dto.DirectorFullDTO;
import br.edu.infnet.tiago.application.dto.DirectorUpdateDTO;
import br.edu.infnet.tiago.domain.model.Director;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    DirectorDTO toDTO(Director director);

    DirectorFullDTO toFullDTO(Director director);

    List<DirectorDTO> toDTO(List<Director> directors);

    @Mapping(source = "countryId", target = "country.id")
    Director fromDTO(DirectorCreateDTO directorCreateDTO);

    @Mapping(source = "countryId", target = "country.id")
    Director fromDTO(DirectorUpdateDTO directorUpdateDTO);
}