package br.edu.infnet.tiago.domain.service;


import br.edu.infnet.tiago.domain.model.Director;
import br.edu.infnet.tiago.domain.repository.DirectorRepository;
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
    public Director update(Director director) {
        return directorRepository.save(director);
    }

    @Transactional
    public void delete(Long directorId) {
        directorRepository.deleteById(directorId);
    }
}