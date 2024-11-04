package br.edu.infnet.tiago.application.controller;

import br.edu.infnet.tiago.application.dto.ActorCreateDTO;
import br.edu.infnet.tiago.application.dto.ActorDTO;
import br.edu.infnet.tiago.application.dto.ActorUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.ActorFilterDTO;
import br.edu.infnet.tiago.domain.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/actors")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @PostMapping
    public ResponseEntity<ActorDTO> create(@RequestBody ActorCreateDTO actor) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping
    public ResponseEntity<Page<ActorDTO>> search(@ModelAttribute ActorFilterDTO filter,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size,
                                                 @RequestParam(defaultValue = "name,asc") String[] sort) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActorDTO> update(@PathVariable Long id, @RequestBody ActorUpdateDTO actor) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
