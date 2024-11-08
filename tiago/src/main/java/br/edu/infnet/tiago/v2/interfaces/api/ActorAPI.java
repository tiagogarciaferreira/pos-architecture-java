package br.edu.infnet.tiago.v2.interfaces.api;

import br.edu.infnet.tiago.application.dto.ActorCreateDTO;
import br.edu.infnet.tiago.application.dto.ActorDTO;
import br.edu.infnet.tiago.application.dto.ActorUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.ActorFilterDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ActorAPI {

    ResponseEntity<ActorDTO> create(@RequestBody ActorCreateDTO actorCreateDTO);

    ResponseEntity<ActorDTO> getById(@PathVariable Long actorId);

    ResponseEntity<ActorDTO> update(@PathVariable Long actorId, @RequestBody ActorUpdateDTO actorUpdateDTO);

    ResponseEntity<Void> delete(@PathVariable Long actorId);

    ResponseEntity<Page<ActorDTO>> search(@ModelAttribute ActorFilterDTO filter,
                                          @RequestParam(defaultValue = "0") @Min(0) int page,
                                          @RequestParam(defaultValue = "10") @Min(10) @Max(100) int size,
                                          @RequestParam(defaultValue = "name,asc") String[] sort);
}