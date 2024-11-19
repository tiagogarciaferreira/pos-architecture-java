package br.edu.infnet.tiago.interfaces.rest;


import br.edu.infnet.tiago.application.dto.DirectorCreateDTO;
import br.edu.infnet.tiago.application.dto.DirectorDTO;
import br.edu.infnet.tiago.application.dto.DirectorFullDTO;
import br.edu.infnet.tiago.application.dto.DirectorUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.DirectorFilterDTO;
import br.edu.infnet.tiago.application.mapper.DirectorMapper;
import br.edu.infnet.tiago.application.utility.SortParameterConverter;
import br.edu.infnet.tiago.domain.model.Director;
import br.edu.infnet.tiago.domain.service.DirectorService;
import br.edu.infnet.tiago.domain.specification.DirectorSpecification;
import br.edu.infnet.tiago.interfaces.api.DirectorAPI;
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

@Tag(name = "Directors", description = "Director management")
@RestController
@RequestMapping("/v1/directors")
@RequiredArgsConstructor
@Slf4j
public class DirectorController implements DirectorAPI {

    private final DirectorMapper directorMapper;

    private final DirectorService directorService;

    @Override
    @PostMapping
    public ResponseEntity<DirectorDTO> create(@RequestBody DirectorCreateDTO directorCreateDTO) {
        Director director = directorMapper.fromDTO(directorCreateDTO);
        director = directorService.create(director);
        DirectorDTO directorDTO = directorMapper.toDTO(director);
        log.info("Director '{}', was successfully created", director.getId());
        return new ResponseEntity<>(directorDTO, CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<DirectorFullDTO> getById(@PathVariable Long id) {
        Director director = directorService.getById(id);
        DirectorFullDTO directorFullDTO = directorMapper.toFullDTO(director);
        log.info("Director '{}', was successfully retrieved", id);
        return new ResponseEntity<>(directorFullDTO, OK);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<DirectorDTO> update(@PathVariable Long id, @RequestBody DirectorUpdateDTO directorUpdateDTO) {
        Director director = directorMapper.fromDTO(directorUpdateDTO);
        director = directorService.update(id, director);
        DirectorDTO directorDTO = directorMapper.toDTO(director);
        log.info("Director '{}', was successfully updated", id);
        return new ResponseEntity<>(directorDTO, OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        directorService.delete(id);
        log.info("Director '{}', was successfully removed", id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<DirectorDTO>> search(@ModelAttribute DirectorFilterDTO filter,
                                                    @RequestParam(defaultValue = "0") @Min(0) int page,
                                                    @RequestParam(defaultValue = "10") @Min(10) @Max(100) int size,
                                                    @RequestParam(defaultValue = "name,asc") String[] sort) {

        Specification<Director> specification = DirectorSpecification.create(filter);
        Pageable pageable = PageRequest.of(page, size, SortParameterConverter.toSort(sort));
        Page<Director> directorPage = directorService.search(specification, pageable);
        List<DirectorDTO> directors = directorMapper.toDTO(directorPage.getContent());
        Page<DirectorDTO> dtoPage = new PageImpl<>(directors, pageable, directorPage.getTotalElements());
        log.info("'{}' directors were successfully listed", directors.size());
        return new ResponseEntity<>(dtoPage, OK);
    }
}