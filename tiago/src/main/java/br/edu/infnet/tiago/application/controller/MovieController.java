package br.edu.infnet.tiago.application.controller;

import br.edu.infnet.tiago.application.converter.SortConverter;
import br.edu.infnet.tiago.application.dto.MovieCreateDTO;
import br.edu.infnet.tiago.application.dto.MovieDTO;
import br.edu.infnet.tiago.application.dto.MovieUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.MovieFilterDTO;
import br.edu.infnet.tiago.application.mapper.MovieMapper;
import br.edu.infnet.tiago.application.specification.MovieSpecification;
import br.edu.infnet.tiago.domain.model.Movie;
import br.edu.infnet.tiago.domain.service.MovieService;
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

@RestController
@RequestMapping("/v1/movies")
@RequiredArgsConstructor
@Slf4j
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieDTO> create(@RequestBody MovieCreateDTO movieCreateDTO) {
        Movie movie = MovieMapper.fromDTO(movieCreateDTO);
        movie = movieService.create(movie);
        MovieDTO movieDTO = MovieMapper.toDTO(movie);
        log.info("Movie '{}', was successfully created", movie.getId());
        return new ResponseEntity<>(movieDTO, CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<MovieDTO>> search(@ModelAttribute MovieFilterDTO filter,
                                                 @RequestParam(defaultValue = "0") @Min(0) int page,
                                                 @RequestParam(defaultValue = "10") @Min(10) @Max(100) int size,
                                                 @RequestParam(defaultValue = "title,asc") String[] sort) {

        Specification<Movie> specification = MovieSpecification.create(filter);
        Pageable pageable = PageRequest.of(page, size, SortConverter.toSort(sort));
        Page<Movie> moviePage = movieService.search(specification, pageable);
        List<MovieDTO> movies = MovieMapper.toDTO(moviePage.getContent());
        Page<MovieDTO> dtoPage = new PageImpl<>(movies, pageable, moviePage.getTotalElements());
        log.info("'{}' movies were successfully listed", movies.size());
        return new ResponseEntity<>(dtoPage, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getById(@PathVariable Long movieId) {
        Movie movie = movieService.getById(movieId);
        MovieDTO movieDTO = MovieMapper.toDTO(movie);
        log.info("Movie '{}', was successfully retrieved", movieId);
        return new ResponseEntity<>(movieDTO, OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long movieId, @RequestBody MovieUpdateDTO movieUpdateDTO) {
        Movie movie = MovieMapper.fromDTO(movieUpdateDTO);
        movie = movieService.update(movie);
        MovieDTO movieDTO = MovieMapper.toDTO(movie);
        log.info("Movie '{}', was successfully updated", movieId);
        return new ResponseEntity<>(movieDTO, OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long movieId) {
        movieService.delete(movieId);
        log.info("Movie '{}', was successfully removed", movieId);
        return new ResponseEntity<>(NO_CONTENT);
    }
}