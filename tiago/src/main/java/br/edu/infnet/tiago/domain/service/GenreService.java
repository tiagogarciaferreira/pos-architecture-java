package br.edu.infnet.tiago.domain.service;


import br.edu.infnet.tiago.domain.model.Genre;
import br.edu.infnet.tiago.domain.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    @Transactional
    public Genre create(Genre genre) {
        return genreRepository.save(genre);
    }

    @Transactional(readOnly = true)
    public Page<Genre> search(Specification<Genre> specification, Pageable pageable) {
        return genreRepository.findAll(specification, pageable);
    }

    @Transactional(readOnly = true)
    public Genre getById(Long genreId) {
        return genreRepository.findById(genreId).get();
    }

    @Transactional
    public Genre update(Genre genre) {
        return genreRepository.save(genre);
    }

    @Transactional
    public void delete(Long genreId) {
        genreRepository.deleteById(genreId);
    }
}