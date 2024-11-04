package br.edu.infnet.tiago.application.mapper;

import br.edu.infnet.tiago.application.dto.GenreCreateDTO;
import br.edu.infnet.tiago.application.dto.GenreDTO;
import br.edu.infnet.tiago.application.dto.GenreUpdateDTO;
import br.edu.infnet.tiago.domain.model.Genre;

public class GenreMapper {

    public static GenreDTO toDTO(Genre genre) {
        return new GenreDTO();
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