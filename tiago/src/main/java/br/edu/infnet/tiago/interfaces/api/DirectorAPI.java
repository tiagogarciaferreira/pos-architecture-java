package br.edu.infnet.tiago.interfaces.api;

import br.edu.infnet.tiago.application.dto.DirectorCreateDTO;
import br.edu.infnet.tiago.application.dto.DirectorDTO;
import br.edu.infnet.tiago.application.dto.DirectorFullDTO;
import br.edu.infnet.tiago.application.dto.DirectorUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.DirectorFilterDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface DirectorAPI {

    @Operation(summary = "Create a new director", description = "Creates a new director in the system.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the director to be created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DirectorCreateDTO.class))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Director created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DirectorDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            })
    ResponseEntity<DirectorDTO> create(@RequestBody DirectorCreateDTO directorCreateDTO);

    @Operation(summary = "Get director by ID", description = "Fetches a director by its ID.",
            parameters = {@Parameter(name = "id", description = "ID of the director", required = true, schema = @Schema(type = "integer", example = "1"))},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Director retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DirectorFullDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Director not found")
            })
    ResponseEntity<DirectorFullDTO> getById(@PathVariable Long id);

    @Operation(summary = "Update director", description = "Updates an existing director's details.",
            parameters = {@Parameter(name = "id", description = "ID of the director to update", required = true, schema = @Schema(type = "integer", example = "1"))},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the director to be updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DirectorUpdateDTO.class))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Director updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DirectorDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "404", description = "Director not found")
            })
    ResponseEntity<DirectorDTO> update(@PathVariable Long id, @RequestBody DirectorUpdateDTO directorUpdateDTO);

    @Operation(summary = "Delete director", description = "Deletes a director by its ID.",
            parameters = {@Parameter(name = "id", description = "ID of the director to delete", required = true, schema = @Schema(type = "integer", example = "1"))},
            responses = {
                    @ApiResponse(responseCode = "204", description = "Director deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Director not found")
            })
    ResponseEntity<Void> delete(@PathVariable Long id);

    @Operation(summary = "Search directors", description = "Search for directors based on the given filters and pagination options.",
            parameters = {
                    @Parameter(name = "filter", description = "Filter criteria for director search", schema = @Schema(implementation = DirectorFilterDTO.class)),
                    @Parameter(name = "page", description = "Page number", schema = @Schema(type = "integer", example = "0")),
                    @Parameter(name = "size", description = "Page size", schema = @Schema(type = "integer", example = "10")),
                    @Parameter(name = "sort", description = "Sort criteria", schema = @Schema(type = "string", example = "name,asc"))
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Directors found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            })
    ResponseEntity<Page<DirectorDTO>> search(@ModelAttribute DirectorFilterDTO filter,
                                             @RequestParam(defaultValue = "0") @Min(0) int page,
                                             @RequestParam(defaultValue = "10") @Min(10) @Max(100) int size,
                                             @RequestParam(defaultValue = "name,asc") String[] sort);
}