package br.edu.infnet.tiago.domain.service;


import br.edu.infnet.tiago.domain.model.Actor;
import br.edu.infnet.tiago.domain.repository.ActorRepository;
import br.edu.infnet.tiago.infrastructure.exception.custom.NotFoundException;
import br.edu.infnet.tiago.shared.utils.ListUtils;
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
        actor = actorRepository.save(actor);
        return getById(actor.getId());
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
        var existingActor = getById(actorId);
        existingActor.setName(actor.getName());
        existingActor.setCountry(actor.getCountry());
        existingActor.setDateOfBirth(actor.getDateOfBirth());
        existingActor.setAwards(ListUtils.getValidValues(actor.getAwards()));
        existingActor.setRoles(ListUtils.getValidValues(actor.getRoles()));
        existingActor.setVersion(actor.getVersion());
        existingActor = actorRepository.save(existingActor);
        return getById(existingActor.getId());
    }

    @Transactional
    public void delete(Long actorId) {
        if (!exists(actorId)) throw new NotFoundException(format("Actor '%s' not found", actorId));
        actorRepository.deleteById(actorId);
    }

    @Transactional(readOnly = true)
    public boolean exists(Long actorId) {
        return actorRepository.existsById(actorId);
    }
}