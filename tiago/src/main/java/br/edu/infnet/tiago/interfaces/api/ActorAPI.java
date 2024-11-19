package br.edu.infnet.tiago.interfaces.api;

import br.edu.infnet.tiago.application.dto.ActorCreateDTO;
import br.edu.infnet.tiago.application.dto.ActorDTO;
import br.edu.infnet.tiago.application.dto.ActorFullDTO;
import br.edu.infnet.tiago.application.dto.ActorUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.ActorFilterDTO;
import br.edu.infnet.tiago.infrastructure.exception.model.Problem;
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

public interface ActorAPI {

    @Operation(summary = "Create a new actor", description = "Creates a new actor in the system.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the actor to be created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActorCreateDTO.class))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Actor created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActorDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
            })
    ResponseEntity<ActorDTO> create(@RequestBody ActorCreateDTO actorCreateDTO);

    @Operation(summary = "Get actor by ID", description = "Fetches a actor by its ID.",
            parameters = {@Parameter(name = "id", description = "ID of the actor", required = true, schema = @Schema(type = "integer", example = "1"))},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Actor retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActorFullDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Actor not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
            })
    ResponseEntity<ActorFullDTO> getById(@PathVariable Long id);

    @Operation(summary = "Update actor", description = "Updates an existing actor's details.",
            parameters = {@Parameter(name = "id", description = "ID of the actor to update", required = true, schema = @Schema(type = "integer", example = "1"))},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the actor to be updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActorUpdateDTO.class))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Actor updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActorDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
                    @ApiResponse(responseCode = "404", description = "Actor not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
            })
    ResponseEntity<ActorDTO> update(@PathVariable Long id, @RequestBody ActorUpdateDTO actorUpdateDTO);

    @Operation(summary = "Delete actor", description = "Deletes a actor by its ID.",
            parameters = {@Parameter(name = "id", description = "ID of the actor to delete", required = true, schema = @Schema(type = "integer", example = "1"))},
            responses = {
                    @ApiResponse(responseCode = "204", description = "Actor deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Actor not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
            })
    ResponseEntity<Void> delete(@PathVariable Long id);

    @Operation(summary = "Search actors", description = "Search for actors based on the given filters and pagination options.",
            parameters = {
                    @Parameter(name = "filter", description = "Filter criteria for actor search", schema = @Schema(implementation = ActorFilterDTO.class)),
                    @Parameter(name = "page", description = "Page number", schema = @Schema(type = "integer", example = "0")),
                    @Parameter(name = "size", description = "Page size", schema = @Schema(type = "integer", example = "10")),
                    @Parameter(name = "sort", description = "Sort criteria", schema = @Schema(type = "string", example = "name,asc"))
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Actors found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
            })
    ResponseEntity<Page<ActorDTO>> search(@ModelAttribute ActorFilterDTO filter,
                                          @RequestParam(defaultValue = "0") @Min(0) int page,
                                          @RequestParam(defaultValue = "10") @Min(10) @Max(100) int size,
                                          @RequestParam(defaultValue = "name,asc") String[] sort);
}