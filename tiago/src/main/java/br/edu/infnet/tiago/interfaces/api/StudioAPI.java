package br.edu.infnet.tiago.interfaces.api;

import br.edu.infnet.tiago.application.dto.StudioCreateDTO;
import br.edu.infnet.tiago.application.dto.StudioDTO;
import br.edu.infnet.tiago.application.dto.StudioFullDTO;
import br.edu.infnet.tiago.application.dto.StudioUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.StudioFilterDTO;
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

public interface StudioAPI {

    @Operation(summary = "Create a new studio", description = "Creates a new studio in the system.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the studio to be created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StudioCreateDTO.class))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Studio created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StudioDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
            })
    ResponseEntity<StudioDTO> create(@RequestBody StudioCreateDTO studioCreateDTO);

    @Operation(summary = "Get studio by ID", description = "Fetches a studio by its ID.",
            parameters = {@Parameter(name = "id", description = "ID of the studio", required = true, schema = @Schema(type = "integer", example = "1"))},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Studio retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StudioFullDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Studio not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
            })
    ResponseEntity<StudioFullDTO> getById(@PathVariable Long id);

    @Operation(summary = "Update studio", description = "Updates an existing studio's details.",
            parameters = {@Parameter(name = "id", description = "ID of the studio to update", required = true, schema = @Schema(type = "integer", example = "1"))},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the studio to be updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StudioUpdateDTO.class))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Studio updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StudioDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
                    @ApiResponse(responseCode = "404", description = "Studio not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
            })
    ResponseEntity<StudioDTO> update(@PathVariable Long id, @RequestBody StudioUpdateDTO studioUpdateDTO);

    @Operation(summary = "Delete studio", description = "Deletes a studio by its ID.",
            parameters = {@Parameter(name = "id", description = "ID of the studio to delete", required = true, schema = @Schema(type = "integer", example = "1"))},
            responses = {
                    @ApiResponse(responseCode = "204", description = "Studio deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Studio not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
            })
    ResponseEntity<Void> delete(@PathVariable Long id);

    @Operation(summary = "Search studios", description = "Search for studios based on the given filters and pagination options.",
            parameters = {
                    @Parameter(name = "filter", description = "Filter criteria for studio search", schema = @Schema(implementation = StudioFilterDTO.class)),
                    @Parameter(name = "page", description = "Page number", schema = @Schema(type = "integer", example = "0")),
                    @Parameter(name = "size", description = "Page size", schema = @Schema(type = "integer", example = "10")),
                    @Parameter(name = "sort", description = "Sort criteria", schema = @Schema(type = "string", example = "name,asc"))
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Studios found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
            })
    ResponseEntity<Page<StudioDTO>> search(@ModelAttribute StudioFilterDTO filter,
                                           @RequestParam(defaultValue = "0") @Min(0) int page,
                                           @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size,
                                           @RequestParam(defaultValue = "name,asc") String[] sort);
}