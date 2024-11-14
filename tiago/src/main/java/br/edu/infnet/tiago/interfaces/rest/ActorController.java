package br.edu.infnet.tiago.interfaces.rest;

import br.edu.infnet.tiago.application.dto.ActorCreateDTO;
import br.edu.infnet.tiago.application.dto.ActorDTO;
import br.edu.infnet.tiago.application.dto.ActorUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.ActorFilterDTO;
import br.edu.infnet.tiago.application.mapper.ActorMapper;
import br.edu.infnet.tiago.application.utility.SortParameterConverter;
import br.edu.infnet.tiago.domain.model.Actor;
import br.edu.infnet.tiago.domain.service.ActorService;
import br.edu.infnet.tiago.interfaces.api.ActorAPI;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "Actors", description = "Actor management")
@RestController
@RequestMapping("/v1/actors")
@RequiredArgsConstructor
@Slf4j
public class ActorController implements ActorAPI {

    private final ActorMapper actorMapper;

    private final ActorService actorService;

    @Override
    @PostMapping
    public ResponseEntity<ActorDTO> create(@RequestBody ActorCreateDTO actorCreateDTO) {
        Actor actor = actorMapper.fromDTO(actorCreateDTO);
        actor = actorService.create(actor);
        ActorDTO actorDTO = actorMapper.toDTO(actor);
        log.info("Actor '{}', was successfully created", actor.getId());
        return new ResponseEntity<>(actorDTO, CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ActorDTO> getById(@PathVariable Long id) {
        Actor actor = actorService.getById(id);
        ActorDTO actorDTO = actorMapper.toDTO(actor);
        log.info("Actor '{}', was successfully retrieved", id);
        return new ResponseEntity<>(actorDTO, OK);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ActorDTO> update(@PathVariable Long id, @RequestBody ActorUpdateDTO actorUpdateDTO) {
        Actor actor = actorMapper.fromDTO(actorUpdateDTO);
        actor = actorService.update(actor);
        ActorDTO actorDTO = actorMapper.toDTO(actor);
        log.info("Actor '{}', was successfully updated", id);
        return new ResponseEntity<>(actorDTO, OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        actorService.delete(id);
        log.info("Actor '{}', was successfully removed", id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ActorDTO>> search(@ModelAttribute ActorFilterDTO filter,
                                                 @RequestParam(defaultValue = "0") @Min(0) int page,
                                                 @RequestParam(defaultValue = "10") @Min(10) @Max(100) int size,
                                                 @RequestParam(defaultValue = "name,asc") String[] sort) {

        Specification<Actor> specification = br.edu.infnet.tiago.domain.specification.ActorSpecification.create(filter);
        Pageable pageable = PageRequest.of(page, size, SortParameterConverter.toSort(sort));
        Page<Actor> actorPage = actorService.search(specification, pageable);
        List<ActorDTO> actors = actorMapper.toDTO(actorPage.getContent());
        Page<ActorDTO> dtoPage = new PageImpl<>(actors, pageable, actorPage.getTotalElements());
        log.info("'{}' actors were successfully listed", actors.size());
        return new ResponseEntity<>(dtoPage, OK);
    }
}