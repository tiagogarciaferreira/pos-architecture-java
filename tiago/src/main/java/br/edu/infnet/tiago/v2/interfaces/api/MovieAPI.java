package br.edu.infnet.tiago.v2.interfaces.api;

import br.edu.infnet.tiago.application.dto.MovieCreateDTO;
import br.edu.infnet.tiago.application.dto.MovieDTO;
import br.edu.infnet.tiago.application.dto.MovieUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.MovieFilterDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface MovieAPI {

    ResponseEntity<MovieDTO> create(@RequestBody MovieCreateDTO movieCreateDTO);

    ResponseEntity<MovieDTO> getById(@PathVariable Long movieId);

    ResponseEntity<MovieDTO> update(@PathVariable Long movieId, @RequestBody MovieUpdateDTO movieUpdateDTO);

    ResponseEntity<Void> delete(@PathVariable Long movieId);

    ResponseEntity<Page<MovieDTO>> search(@ModelAttribute MovieFilterDTO filter,
                                          @RequestParam(defaultValue = "0") @Min(0) int page,
                                          @RequestParam(defaultValue = "10") @Min(10) @Max(100) int size,
                                          @RequestParam(defaultValue = "title,asc") String[] sort);
}