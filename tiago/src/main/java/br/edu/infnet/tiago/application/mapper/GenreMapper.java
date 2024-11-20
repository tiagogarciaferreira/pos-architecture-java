package br.edu.infnet.tiago.application.mapper;

import br.edu.infnet.tiago.application.dto.GenreCreateDTO;
import br.edu.infnet.tiago.application.dto.GenreDTO;
import br.edu.infnet.tiago.application.dto.GenreFullDTO;
import br.edu.infnet.tiago.application.dto.GenreUpdateDTO;
import br.edu.infnet.tiago.domain.model.Genre;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDTO toDTO(Genre genre);

    GenreFullDTO toFullDTO(Genre genre);

    List<GenreDTO> toDTO(List<Genre> genres);

    Genre fromDTO(GenreCreateDTO genreCreateDTO);

    Genre fromDTO(GenreUpdateDTO genreUpdateDTO);
}