package br.edu.infnet.tiago.application.mapper;

import br.edu.infnet.tiago.application.dto.MovieCreateDTO;
import br.edu.infnet.tiago.application.dto.MovieDTO;
import br.edu.infnet.tiago.application.dto.MovieFullDTO;
import br.edu.infnet.tiago.application.dto.MovieUpdateDTO;
import br.edu.infnet.tiago.domain.model.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.edu.infnet.tiago.shared.utils.ListUtils.defaultIfNull;

@Component
@RequiredArgsConstructor
public class MovieMapper {

    private final MapperFactory mapperFactory;

    public MovieDTO toDTO(Movie movie) {
        return mapperFactory.mapToNewInstance(movie, MovieDTO.class);
    }

    public MovieFullDTO toFullDTO(Movie movie) {
        return mapperFactory.mapToNewInstance(movie, MovieFullDTO.class);
    }

    public List<MovieDTO> toDTO(List<Movie> movies) {
        return defaultIfNull(movies).stream().map(this::toDTO).toList();
    }

    public Movie fromDTO(MovieCreateDTO movieCreateDTO) {
        return mapperFactory.mapToNewInstance(movieCreateDTO, Movie.class);
    }

    public Movie fromDTO(MovieUpdateDTO movieUpdateDTO) {
        return mapperFactory.mapToNewInstance(movieUpdateDTO, Movie.class);
    }
}