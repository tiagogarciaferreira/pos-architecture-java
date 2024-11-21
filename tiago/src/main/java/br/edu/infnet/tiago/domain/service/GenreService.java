package br.edu.infnet.tiago.domain.service;


import br.edu.infnet.tiago.domain.model.Genre;
import br.edu.infnet.tiago.domain.repository.GenreRepository;
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
public class GenreService {

    private final GenreRepository genreRepository;

    @Transactional
    public Genre create(Genre genre) {
        genre = genreRepository.save(genre);
        return getById(genre.getId());
    }

    @Transactional(readOnly = true)
    public Page<Genre> search(Specification<Genre> specification, Pageable pageable) {
        return genreRepository.findAll(specification, pageable);
    }

    @Transactional(readOnly = true)
    public Genre getById(Long genreId) {
        return genreRepository.findById(genreId)
                .orElseThrow(() -> new NotFoundException(format("Genre '%s' not found", genreId)));
    }

    @Transactional
    public Genre update(Long genreId, Genre genre) {
        var existingGenre = getById(genreId).withName(genre.getName()).withVersion(genre.getVersion());
        existingGenre = genreRepository.save(existingGenre);
        return getById(existingGenre.getId());
    }

    @Transactional
    public void delete(Long genreId) {
        if (!exists(genreId)) throw new NotFoundException(format("Genre '%s' not found", genreId));
        genreRepository.deleteById(genreId);
    }

    @Transactional(readOnly = true)
    public boolean exists(Long genreId) {
        return genreRepository.existsById(genreId);
    }
}