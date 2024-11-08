package br.edu.infnet.tiago.v2.application.mapper;

import br.edu.infnet.tiago.application.dto.ActorCreateDTO;
import br.edu.infnet.tiago.application.dto.ActorDTO;
import br.edu.infnet.tiago.application.dto.ActorUpdateDTO;
import br.edu.infnet.tiago.domain.model.Actor;

import java.util.List;

public class ActorMapper {

    public static ActorDTO toDTO(Actor actor) {

        return new ActorDTO();
    }

    public static List<ActorDTO> toDTO(List<Actor> actors) {
        return actors.stream().map(ActorMapper::toDTO).toList();
    }

    public static Actor fromDTO(ActorDTO actorDTO) {
        return new Actor();
    }

    public static Actor fromDTO(ActorCreateDTO actorCreateDTO) {
        return new Actor();
    }

    public static Actor fromDTO(ActorUpdateDTO actorUpdateDTO) {
        return new Actor();
    }
}