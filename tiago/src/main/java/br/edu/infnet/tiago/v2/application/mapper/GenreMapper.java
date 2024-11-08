package br.edu.infnet.tiago.v2.application.mapper;

import br.edu.infnet.tiago.application.dto.GenreCreateDTO;
import br.edu.infnet.tiago.application.dto.GenreDTO;
import br.edu.infnet.tiago.application.dto.GenreUpdateDTO;
import br.edu.infnet.tiago.domain.model.Genre;

import java.util.List;

public class GenreMapper {

    public static GenreDTO toDTO(Genre genre) {
        return new GenreDTO();
    }

    public static List<GenreDTO> toDTO(List<Genre> genres) {
        return genres.stream().map(GenreMapper::toDTO).toList();
    }

    public static Genre fromDTO(GenreDTO genreDTO) {
        return new Genre();
    }

    public static Genre fromDTO(GenreCreateDTO genreCreateDTO) {
        return new Genre();
    }

    public static Genre fromDTO(GenreUpdateDTO genreUpdateDTO) {
        return new Genre();
    }
}