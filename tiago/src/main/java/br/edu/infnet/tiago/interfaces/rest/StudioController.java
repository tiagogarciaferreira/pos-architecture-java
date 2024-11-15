package br.edu.infnet.tiago.interfaces.rest;

import br.edu.infnet.tiago.application.dto.StudioCreateDTO;
import br.edu.infnet.tiago.application.dto.StudioDTO;
import br.edu.infnet.tiago.application.dto.StudioFullDTO;
import br.edu.infnet.tiago.application.dto.StudioUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.StudioFilterDTO;
import br.edu.infnet.tiago.application.mapper.StudioMapper;
import br.edu.infnet.tiago.application.utility.SortParameterConverter;
import br.edu.infnet.tiago.domain.model.Studio;
import br.edu.infnet.tiago.domain.service.StudioService;
import br.edu.infnet.tiago.domain.specification.StudioSpecification;
import br.edu.infnet.tiago.interfaces.api.StudioAPI;
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

@Tag(name = "Studios", description = "Studio management")
@RestController
@RequestMapping("/v1/studios")
@RequiredArgsConstructor
@Slf4j
public class StudioController implements StudioAPI {

    private final StudioMapper studioMapper;

    private final StudioService studioService;

    @Override
    @PostMapping
    public ResponseEntity<StudioDTO> create(@RequestBody StudioCreateDTO studioCreateDTO) {
        Studio studio = studioMapper.fromDTO(studioCreateDTO);
        studio = studioService.create(studio);
        StudioDTO studioDTO = studioMapper.toDTO(studio);
        log.info("Studio '{}', was successfully created", studio.getId());
        return new ResponseEntity<>(studioDTO, CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<StudioFullDTO> getById(@PathVariable Long id) {
        Studio studio = studioService.getById(id);
        StudioFullDTO studioFullDTO = studioMapper.toFullDTO(studio);
        log.info("Studio '{}', was successfully retrieved", id);
        return new ResponseEntity<>(studioFullDTO, OK);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<StudioDTO> update(@PathVariable Long id, @RequestBody StudioUpdateDTO studioUpdateDTO) {
        Studio studio = studioMapper.fromDTO(studioUpdateDTO);
        studio = studioService.update(studio);
        StudioDTO studioDTO = studioMapper.toDTO(studio);
        log.info("Studio '{}', was successfully updated", id);
        return new ResponseEntity<>(studioDTO, OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studioService.delete(id);
        log.info("Studio '{}', was successfully removed", id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<StudioDTO>> search(@ModelAttribute StudioFilterDTO filter,
                                                  @RequestParam(defaultValue = "0") @Min(0) int page,
                                                  @RequestParam(defaultValue = "10") @Min(10) @Max(100) int size,
                                                  @RequestParam(defaultValue = "name,asc") String[] sort) {

        Specification<Studio> specification = StudioSpecification.create(filter);
        Pageable pageable = PageRequest.of(page, size, SortParameterConverter.toSort(sort));
        Page<Studio> studioPage = studioService.search(specification, pageable);
        List<StudioDTO> studios = studioMapper.toDTO(studioPage.getContent());
        Page<StudioDTO> dtoPage = new PageImpl<>(studios, pageable, studioPage.getTotalElements());
        log.info("'{}' studios were successfully listed", studios.size());
        return new ResponseEntity<>(dtoPage, OK);
    }
}