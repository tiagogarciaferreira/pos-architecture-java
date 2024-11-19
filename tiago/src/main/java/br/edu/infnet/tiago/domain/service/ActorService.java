package br.edu.infnet.tiago.domain.service;


import br.edu.infnet.tiago.domain.model.Actor;
import br.edu.infnet.tiago.domain.repository.ActorRepository;
import br.edu.infnet.tiago.infrastructure.exception.custom.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;

    @Transactional
    public Actor create(Actor actor) {
        return actorRepository.save(actor);
    }

    @Transactional(readOnly = true)
    public Page<Actor> search(Specification<Actor> specification, Pageable pageable) {
        return actorRepository.findAll(specification, pageable);
    }

    @Transactional(readOnly = true)
    public Actor getById(Long actorId) {
        return actorRepository.findById(actorId)
                .orElseThrow(() -> new NotFoundException(format("Actor '%s' not found", actorId)));
    }

    @Transactional
    public Actor update(Long actorId, Actor actor) {
        Actor actorFound = getById(actorId);
        actor.setId(actorFound.getId());
        return actorRepository.save(actor);
    }

    @Transactional
    public void delete(Long actorId) {
        actorRepository.deleteById(actorId);
    }
}