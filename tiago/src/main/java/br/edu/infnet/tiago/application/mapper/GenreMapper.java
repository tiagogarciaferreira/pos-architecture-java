package br.edu.infnet.tiago.application.mapper;

import br.edu.infnet.tiago.application.dto.GenreCreateDTO;
import br.edu.infnet.tiago.application.dto.GenreDTO;
import br.edu.infnet.tiago.application.dto.GenreFullDTO;
import br.edu.infnet.tiago.application.dto.GenreUpdateDTO;
import br.edu.infnet.tiago.domain.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.edu.infnet.tiago.shared.utils.ListUtils.defaultIfNull;

@Component
@RequiredArgsConstructor
public class GenreMapper {

    private final MapperFactory mapperFactory;

    public GenreDTO toDTO(Genre genre) {
        return mapperFactory.mapToNewInstance(genre, GenreDTO.class);
    }

    public GenreFullDTO toFullDTO(Genre genre) {
        return mapperFactory.mapToNewInstance(genre, GenreFullDTO.class);
    }

    public List<GenreDTO> toDTO(List<Genre> genres) {
        return defaultIfNull(genres).stream().map(this::toDTO).toList();
    }

    public Genre fromDTO(GenreCreateDTO genreCreateDTO) {
        return mapperFactory.mapToNewInstance(genreCreateDTO, Genre.class);
    }

    public Genre fromDTO(GenreUpdateDTO genreUpdateDTO) {
        return mapperFactory.mapToNewInstance(genreUpdateDTO, Genre.class);
    }

}