package br.edu.infnet.tiago.application.controller;

import br.edu.infnet.tiago.application.dto.StudioCreateDTO;
import br.edu.infnet.tiago.application.dto.StudioDTO;
import br.edu.infnet.tiago.application.dto.StudioUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.StudioFilterDTO;
import br.edu.infnet.tiago.domain.service.StudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/studios")
@RequiredArgsConstructor
public class StudioController {

    private final StudioService studioService;

    @PostMapping
    public ResponseEntity<StudioDTO> create(@RequestBody StudioCreateDTO studio) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping
    public ResponseEntity<Page<StudioDTO>> search(@ModelAttribute StudioFilterDTO filter,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size,
                                                  @RequestParam(defaultValue = "name,asc") String[] sort) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudioDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudioDTO> update(@PathVariable Long id, @RequestBody StudioUpdateDTO studio) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
