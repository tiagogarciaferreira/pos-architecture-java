package br.edu.infnet.tiago.v2.interfaces.rest;

import br.edu.infnet.tiago.application.converter.SortConverter;
import br.edu.infnet.tiago.application.dto.StudioCreateDTO;
import br.edu.infnet.tiago.application.dto.StudioDTO;
import br.edu.infnet.tiago.application.dto.StudioUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.StudioFilterDTO;
import br.edu.infnet.tiago.application.mapper.StudioMapper;
import br.edu.infnet.tiago.application.specification.StudioSpecification;
import br.edu.infnet.tiago.domain.model.Studio;
import br.edu.infnet.tiago.domain.service.StudioService;
import br.edu.infnet.tiago.v2.interfaces.api.StudioAPI;
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
@RequestMapping("/v1/studios")
@RequiredArgsConstructor
@Slf4j
public class StudioController implements StudioAPI {

    private final StudioService studioService;

    @Override
    @PostMapping
    public ResponseEntity<StudioDTO> create(@RequestBody StudioCreateDTO studioCreateDTO) {
        Studio studio = StudioMapper.fromDTO(studioCreateDTO);
        studio = studioService.create(studio);
        StudioDTO studioDTO = StudioMapper.toDTO(studio);
        log.info("Studio '{}', was successfully created", studio.getId());
        return new ResponseEntity<>(studioDTO, CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<StudioDTO> getById(@PathVariable Long studioId) {
        Studio studio = studioService.getById(studioId);
        StudioDTO studioDTO = StudioMapper.toDTO(studio);
        log.info("Studio '{}', was successfully retrieved", studioId);
        return new ResponseEntity<>(studioDTO, OK);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<StudioDTO> update(@PathVariable Long studioId, @RequestBody StudioUpdateDTO studioUpdateDTO) {
        Studio studio = StudioMapper.fromDTO(studioUpdateDTO);
        studio = studioService.update(studio);
        StudioDTO studioDTO = StudioMapper.toDTO(studio);
        log.info("Studio '{}', was successfully updated", studioId);
        return new ResponseEntity<>(studioDTO, OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long studioId) {
        studioService.delete(studioId);
        log.info("Studio '{}', was successfully removed", studioId);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<StudioDTO>> search(@ModelAttribute StudioFilterDTO filter,
                                                  @RequestParam(defaultValue = "0") @Min(0) int page,
                                                  @RequestParam(defaultValue = "10") @Min(10) @Max(100) int size,
                                                  @RequestParam(defaultValue = "name,asc") String[] sort) {

        Specification<Studio> specification = StudioSpecification.create(filter);
        Pageable pageable = PageRequest.of(page, size, SortConverter.toSort(sort));
        Page<Studio> studioPage = studioService.search(specification, pageable);
        List<StudioDTO> studios = StudioMapper.toDTO(studioPage.getContent());
        Page<StudioDTO> dtoPage = new PageImpl<>(studios, pageable, studioPage.getTotalElements());
        log.info("'{}' studios were successfully listed", studios.size());
        return new ResponseEntity<>(dtoPage, OK);
    }
}