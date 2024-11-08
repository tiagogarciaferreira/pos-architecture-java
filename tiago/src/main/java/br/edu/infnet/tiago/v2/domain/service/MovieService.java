package br.edu.infnet.tiago.v2.domain.service;


import br.edu.infnet.tiago.v2.domain.model.Movie;
import br.edu.infnet.tiago.v2.domain.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional
    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }

    @Transactional(readOnly = true)
    public Page<Movie> search(Specification<Movie> specification, Pageable pageable) {
        return movieRepository.findAll(specification, pageable);
    }

    @Transactional(readOnly = true)
    public Movie getById(Long movieId) {
        return movieRepository.findById(movieId).get();
    }

    @Transactional
    public Movie update(Movie movie) {
        return movieRepository.save(movie);
    }

    @Transactional
    public void delete(Long movieId) {
        movieRepository.deleteById(movieId);
    }
}