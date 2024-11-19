package br.edu.infnet.tiago.domain.service;


import br.edu.infnet.tiago.domain.model.Movie;
import br.edu.infnet.tiago.domain.repository.MovieRepository;
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
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new NotFoundException(format("Movie '%s' not found", movieId)));
    }

    @Transactional
    public Movie update(Long movieId, Movie movie) {
        var existingMovie = getById(movieId)
                .withImdbId(movie.getImdbId())
                .withTitle(movie.getTitle())
                .withSynopsis(movie.getSynopsis())
                .withCountry(movie.getCountry())
                .withActors(movie.getActors())
                .withStudio(movie.getStudio())
                .withDirector(movie.getDirector())
                .withBoxOfficeDollars(movie.getBoxOfficeDollars())
                .withBudgetDollars(movie.getBudgetDollars())
                .withReleaseDate(movie.getReleaseDate())
                .withDurationMinutes(movie.getDurationMinutes())
                .withGenres(movie.getGenres())
                .withImdbRating(movie.getImdbRating())
                .withPoster(movie.getPoster())
                .withLanguages(movie.getLanguages())
                .withSubtitles(movie.getSubtitles())
                .withVersion(movie.getVersion());
        return movieRepository.save(existingMovie);
    }

    @Transactional
    public void delete(Long movieId) {
        movieRepository.deleteById(movieId);
    }
}