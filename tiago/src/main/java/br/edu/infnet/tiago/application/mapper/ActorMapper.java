package br.edu.infnet.tiago.application.mapper;

import br.edu.infnet.tiago.application.dto.ActorCreateDTO;
import br.edu.infnet.tiago.application.dto.ActorDTO;
import br.edu.infnet.tiago.application.dto.ActorFullDTO;
import br.edu.infnet.tiago.application.dto.ActorUpdateDTO;
import br.edu.infnet.tiago.domain.model.Actor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.edu.infnet.tiago.shared.utils.ListUtils.defaultIfNull;

@Component
@RequiredArgsConstructor
public class ActorMapper {

    private final MapperFactory mapperFactory;

    public ActorDTO toDTO(Actor actor) {
        return mapperFactory.mapToNewInstance(actor, ActorDTO.class);
    }

    public ActorFullDTO toFullDTO(Actor actor) {
        return mapperFactory.mapToNewInstance(actor, ActorFullDTO.class);
    }

    public List<ActorDTO> toDTO(List<Actor> actors) {
        return defaultIfNull(actors).stream().map(this::toDTO).toList();
    }

    public Actor fromDTO(ActorCreateDTO actorCreateDTO) {
        return mapperFactory.mapToNewInstance(actorCreateDTO, Actor.class);
    }

    public Actor fromDTO(ActorUpdateDTO actorUpdateDTO) {
        return mapperFactory.mapToNewInstance(actorUpdateDTO, Actor.class);
    }
}