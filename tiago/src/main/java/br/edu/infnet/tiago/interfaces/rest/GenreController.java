package br.edu.infnet.tiago.interfaces.rest;

import br.edu.infnet.tiago.application.dto.GenreCreateDTO;
import br.edu.infnet.tiago.application.dto.GenreDTO;
import br.edu.infnet.tiago.application.dto.GenreFullDTO;
import br.edu.infnet.tiago.application.dto.GenreUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.GenreFilterDTO;
import br.edu.infnet.tiago.application.mapper.GenreMapper;
import br.edu.infnet.tiago.application.utility.SortParameterConverter;
import br.edu.infnet.tiago.domain.model.Genre;
import br.edu.infnet.tiago.domain.service.GenreService;
import br.edu.infnet.tiago.domain.specification.GenreSpecification;
import br.edu.infnet.tiago.interfaces.api.GenreAPI;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Genres", description = "Genre management")
@RestController
@RequestMapping("/v1/genres")
@RequiredArgsConstructor
@Slf4j
public class GenreController implements GenreAPI {

    private final GenreMapper genreMapper;

    private final GenreService genreService;

    @Override
    @PostMapping
    public ResponseEntity<GenreDTO> create(@RequestBody GenreCreateDTO genreCreateDTO) {
        Genre genre = genreMapper.fromDTO(genreCreateDTO);
        genre = genreService.create(genre);
        GenreDTO genreDTO = genreMapper.toDTO(genre);
        log.info("Genre '{}', was successfully created", genre.getId());
        return new ResponseEntity<>(genreDTO, CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<GenreFullDTO> getById(@PathVariable Long id) {
        Genre genre = genreService.getById(id);
        GenreFullDTO genreFullDTO = genreMapper.toFullDTO(genre);
        log.info("Genre '{}', was successfully retrieved", id);
        return new ResponseEntity<>(genreFullDTO, OK);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> update(@PathVariable Long id, @RequestBody GenreUpdateDTO genreUpdateDTO) {
        Genre genre = genreMapper.fromDTO(genreUpdateDTO);
        genre = genreService.update(genre);
        GenreDTO genreDTO = genreMapper.toDTO(genre);
        log.info("Genre '{}', was successfully updated", id);
        return new ResponseEntity<>(genreDTO, OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        genreService.delete(id);
        log.info("Genre '{}', was successfully removed", id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<GenreDTO>> search(@ModelAttribute GenreFilterDTO filter,
                                                 @RequestParam(defaultValue = "0") @Min(0) int page,
                                                 @RequestParam(defaultValue = "10") @Min(10) @Max(100) int size,
                                                 @RequestParam(defaultValue = "name,asc") String[] sort) {

        Specification<Genre> specification = GenreSpecification.create(filter);
        Pageable pageable = PageRequest.of(page, size, SortParameterConverter.toSort(sort));
        Page<Genre> genrePage = genreService.search(specification, pageable);
        List<GenreDTO> genres = genreMapper.toDTO(genrePage.getContent());
        Page<GenreDTO> dtoPage = new PageImpl<>(genres, pageable, genrePage.getTotalElements());
        log.info("'{}' genres were successfully listed", genres.size());
        return new ResponseEntity<>(dtoPage, OK);
    }
}