package br.edu.infnet.tiago.application.controller;

import br.edu.infnet.tiago.application.dto.DirectorCreateDTO;
import br.edu.infnet.tiago.application.dto.DirectorDTO;
import br.edu.infnet.tiago.application.dto.DirectorUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.DirectorFilterDTO;
import br.edu.infnet.tiago.domain.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/directors")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<DirectorDTO> create(@RequestBody DirectorCreateDTO director) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping
    public ResponseEntity<Page<DirectorDTO>> search(@ModelAttribute DirectorFilterDTO filter,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size,
                                                    @RequestParam(defaultValue = "name,asc") String[] sort) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectorDTO> update(@PathVariable Long id, @RequestBody DirectorUpdateDTO director) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}