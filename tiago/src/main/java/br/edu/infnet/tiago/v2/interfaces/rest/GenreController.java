package br.edu.infnet.tiago.v2.interfaces.rest;

import br.edu.infnet.tiago.application.converter.SortConverter;
import br.edu.infnet.tiago.application.dto.GenreCreateDTO;
import br.edu.infnet.tiago.application.dto.GenreDTO;
import br.edu.infnet.tiago.application.dto.GenreUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.GenreFilterDTO;
import br.edu.infnet.tiago.application.mapper.GenreMapper;
import br.edu.infnet.tiago.application.specification.GenreSpecification;
import br.edu.infnet.tiago.domain.model.Genre;
import br.edu.infnet.tiago.domain.service.GenreService;
import br.edu.infnet.tiago.v2.interfaces.api.GenreAPI;
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
@RequestMapping("/v1/genres")
@RequiredArgsConstructor
@Slf4j
public class GenreController implements GenreAPI {

    private final GenreService genreService;

    @Override
    @PostMapping
    public ResponseEntity<GenreDTO> create(@RequestBody GenreCreateDTO genreCreateDTO) {
        Genre genre = GenreMapper.fromDTO(genreCreateDTO);
        genre = genreService.create(genre);
        GenreDTO genreDTO = GenreMapper.toDTO(genre);
        log.info("Genre '{}', was successfully created", genre.getId());
        return new ResponseEntity<>(genreDTO, CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getById(@PathVariable Long genreId) {
        Genre genre = genreService.getById(genreId);
        GenreDTO genreDTO = GenreMapper.toDTO(genre);
        log.info("Genre '{}', was successfully retrieved", genreId);
        return new ResponseEntity<>(genreDTO, OK);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> update(@PathVariable Long genreId, @RequestBody GenreUpdateDTO genreUpdateDTO) {
        Genre genre = GenreMapper.fromDTO(genreUpdateDTO);
        genre = genreService.update(genre);
        GenreDTO genreDTO = GenreMapper.toDTO(genre);
        log.info("Genre '{}', was successfully updated", genreId);
        return new ResponseEntity<>(genreDTO, OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long genreId) {
        genreService.delete(genreId);
        log.info("Genre '{}', was successfully removed", genreId);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<GenreDTO>> search(@ModelAttribute GenreFilterDTO filter,
                                                 @RequestParam(defaultValue = "0") @Min(0) int page,
                                                 @RequestParam(defaultValue = "10") @Min(10) @Max(100) int size,
                                                 @RequestParam(defaultValue = "name,asc") String[] sort) {

        Specification<Genre> specification = GenreSpecification.create(filter);
        Pageable pageable = PageRequest.of(page, size, SortConverter.toSort(sort));
        Page<Genre> genrePage = genreService.search(specification, pageable);
        List<GenreDTO> genres = GenreMapper.toDTO(genrePage.getContent());
        Page<GenreDTO> dtoPage = new PageImpl<>(genres, pageable, genrePage.getTotalElements());
        log.info("'{}' genres were successfully listed", genres.size());
        return new ResponseEntity<>(dtoPage, OK);
    }
}