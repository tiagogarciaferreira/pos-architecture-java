package br.edu.infnet.tiago.application.controller;

import br.edu.infnet.tiago.application.dto.GenreCreateDTO;
import br.edu.infnet.tiago.application.dto.GenreDTO;
import br.edu.infnet.tiago.application.dto.GenreUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.GenreFilterDTO;
import br.edu.infnet.tiago.domain.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @PostMapping
    public ResponseEntity<GenreDTO> create(@RequestBody GenreCreateDTO genre) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping
    public ResponseEntity<Page<GenreDTO>> search(@ModelAttribute GenreFilterDTO filter,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size,
                                                 @RequestParam(defaultValue = "name,asc") String[] sort) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> update(@PathVariable Long id, @RequestBody GenreUpdateDTO genre) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
