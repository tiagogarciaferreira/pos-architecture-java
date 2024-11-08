package br.edu.infnet.tiago.v2.interfaces.api;

import br.edu.infnet.tiago.application.dto.GenreCreateDTO;
import br.edu.infnet.tiago.application.dto.GenreDTO;
import br.edu.infnet.tiago.application.dto.GenreUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.GenreFilterDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface GenreAPI {

    ResponseEntity<GenreDTO> create(@RequestBody GenreCreateDTO genreCreateDTO);

    ResponseEntity<GenreDTO> getById(@PathVariable Long genreId);

    ResponseEntity<GenreDTO> update(@PathVariable Long genreId, @RequestBody GenreUpdateDTO genreUpdateDTO);

    ResponseEntity<Void> delete(@PathVariable Long genreId);

    ResponseEntity<Page<GenreDTO>> search(@ModelAttribute GenreFilterDTO filter,
                                          @RequestParam(defaultValue = "0") @Min(0) int page,
                                          @RequestParam(defaultValue = "10") @Min(10) @Max(100) int size,
                                          @RequestParam(defaultValue = "name,asc") String[] sort);
}