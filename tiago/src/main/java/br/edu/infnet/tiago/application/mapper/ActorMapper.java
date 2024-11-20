package br.edu.infnet.tiago.application.mapper;

import br.edu.infnet.tiago.application.dto.ActorCreateDTO;
import br.edu.infnet.tiago.application.dto.ActorDTO;
import br.edu.infnet.tiago.application.dto.ActorFullDTO;
import br.edu.infnet.tiago.application.dto.ActorUpdateDTO;
import br.edu.infnet.tiago.domain.model.Actor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActorMapper {

    ActorDTO toDTO(Actor actor);

    ActorFullDTO toFullDTO(Actor actor);

    List<ActorDTO> toDTO(List<Actor> actors);

    @Mapping(source = "countryId", target = "country.id")
    Actor fromDTO(ActorCreateDTO actorCreateDTO);

    @Mapping(source = "countryId", target = "country.id")
    Actor fromDTO(ActorUpdateDTO actorUpdateDTO);
}