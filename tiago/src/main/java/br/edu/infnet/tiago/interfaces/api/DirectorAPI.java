package br.edu.infnet.tiago.interfaces.api;

import br.edu.infnet.tiago.application.dto.DirectorCreateDTO;
import br.edu.infnet.tiago.application.dto.DirectorDTO;
import br.edu.infnet.tiago.application.dto.DirectorUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.DirectorFilterDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface DirectorAPI {

    ResponseEntity<DirectorDTO> create(@RequestBody DirectorCreateDTO directorCreateDTO);

    ResponseEntity<DirectorDTO> getById(@PathVariable Long directorId);

    ResponseEntity<DirectorDTO> update(@PathVariable Long directorId, @RequestBody DirectorUpdateDTO directorUpdateDTO);

    ResponseEntity<Void> delete(@PathVariable Long directorId);

    ResponseEntity<Page<DirectorDTO>> search(@ModelAttribute DirectorFilterDTO filter,
                                             @RequestParam(defaultValue = "0") @Min(0) int page,
                                             @RequestParam(defaultValue = "10") @Min(10) @Max(100) int size,
                                             @RequestParam(defaultValue = "name,asc") String[] sort);
}