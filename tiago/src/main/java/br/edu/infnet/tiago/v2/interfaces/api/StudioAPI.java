package br.edu.infnet.tiago.v2.interfaces.api;

import br.edu.infnet.tiago.application.dto.StudioCreateDTO;
import br.edu.infnet.tiago.application.dto.StudioDTO;
import br.edu.infnet.tiago.application.dto.StudioUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.StudioFilterDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface StudioAPI {

    ResponseEntity<StudioDTO> create(@RequestBody StudioCreateDTO studioCreateDTO);

    ResponseEntity<StudioDTO> getById(@PathVariable Long studioId);

    ResponseEntity<StudioDTO> update(@PathVariable Long studioId, @RequestBody StudioUpdateDTO studioUpdateDTO);

    ResponseEntity<Void> delete(@PathVariable Long studioId);

    ResponseEntity<Page<StudioDTO>> search(@ModelAttribute StudioFilterDTO filter,
                                           @RequestParam(defaultValue = "0") @Min(0) int page,
                                           @RequestParam(defaultValue = "10") @Min(10) @Max(100) int size,
                                           @RequestParam(defaultValue = "name,asc") String[] sort);
}