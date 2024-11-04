package br.edu.infnet.tiago.application.mapper;

import br.edu.infnet.tiago.application.dto.MovieCreateDTO;
import br.edu.infnet.tiago.application.dto.MovieDTO;
import br.edu.infnet.tiago.application.dto.MovieUpdateDTO;
import br.edu.infnet.tiago.domain.model.Movie;

public class MovieMapper {

    public static MovieDTO toDTO(Movie movie) {
        return new MovieDTO();
    }

    public static Movie fromDTO(MovieDTO movieDTO) {
        return new Movie();
    }

    public static Movie fromDTO(MovieCreateDTO movieCreateDTO) {
        return new Movie();
    }

    public static Movie fromDTO(MovieUpdateDTO movieUpdateDTO) {
        return new Movie();
    }
}