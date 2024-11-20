package br.edu.infnet.tiago.domain.service;


import br.edu.infnet.tiago.domain.model.Director;
import br.edu.infnet.tiago.domain.repository.DirectorRepository;
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
public class DirectorService {

    private final DirectorRepository directorRepository;

    @Transactional
    public Director create(Director director) {
        return directorRepository.save(director);
    }

    @Transactional(readOnly = true)
    public Page<Director> search(Specification<Director> specification, Pageable pageable) {
        return directorRepository.findAll(specification, pageable);
    }

    @Transactional(readOnly = true)
    public Director getById(Long directorId) {
        return directorRepository.findById(directorId)
                .orElseThrow(() -> new NotFoundException(format("Director '%s' not found", directorId)));
    }

    @Transactional
    public Director update(Long directorId, Director director) {
        var existingDirector = getById(directorId);
        existingDirector.setName(director.getName());
        existingDirector.setCountry(director.getCountry());
        existingDirector.setAwards(ListUtils.getValidValues(director.getAwards()));
        existingDirector.setDateOfBirth(director.getDateOfBirth());
        existingDirector.setVersion(director.getVersion());
        return directorRepository.save(existingDirector);
    }

    @Transactional
    public void delete(Long directorId) {
        if (!exists(directorId)) throw new NotFoundException(format("Director '%s' not found", directorId));
        directorRepository.deleteById(directorId);
    }

    @Transactional(readOnly = true)
    public boolean exists(Long directorId) {
        return directorRepository.existsById(directorId);
    }
}