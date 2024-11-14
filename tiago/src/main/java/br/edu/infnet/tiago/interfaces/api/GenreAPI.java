package br.edu.infnet.tiago.interfaces.api;

import br.edu.infnet.tiago.application.dto.GenreCreateDTO;
import br.edu.infnet.tiago.application.dto.GenreDTO;
import br.edu.infnet.tiago.application.dto.GenreUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.GenreFilterDTO;
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

public interface GenreAPI {

    @Operation(summary = "Create a new genre", description = "Creates a new genre in the system.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the genre to be created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenreCreateDTO.class))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Genre created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenreDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            })
    ResponseEntity<GenreDTO> create(@RequestBody GenreCreateDTO genreCreateDTO);

    @Operation(summary = "Get genre by ID", description = "Fetches a genre by its ID.",
            parameters = {@Parameter(name = "id", description = "ID of the genre", required = true, schema = @Schema(type = "integer", example = "1"))},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Genre retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenreDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Genre not found")
            })
    ResponseEntity<GenreDTO> getById(@PathVariable("id") Long genreId);

    @Operation(summary = "Update genre", description = "Updates an existing genre's details.",
            parameters = {@Parameter(name = "genreId", description = "ID of the genre to update", required = true, schema = @Schema(type = "integer", example = "1"))},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the genre to be updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenreUpdateDTO.class))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Genre updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenreDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "404", description = "Genre not found")
            })
    ResponseEntity<GenreDTO> update(@PathVariable Long genreId, @RequestBody GenreUpdateDTO genreUpdateDTO);

    @Operation(summary = "Delete genre", description = "Deletes a genre by its ID.",
            parameters = {@Parameter(name = "genreId", description = "ID of the genre to delete", required = true, schema = @Schema(type = "integer", example = "1"))},
            responses = {
                    @ApiResponse(responseCode = "204", description = "Genre deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Genre not found")
            })
    ResponseEntity<Void> delete(@PathVariable Long genreId);

    @Operation(summary = "Search genres", description = "Search for genres based on the given filters and pagination options.",
            parameters = {
                    @Parameter(name = "filter", description = "Filter criteria for genre search", schema = @Schema(implementation = GenreFilterDTO.class)),
                    @Parameter(name = "page", description = "Page number", schema = @Schema(type = "integer", example = "0")),
                    @Parameter(name = "size", description = "Page size", schema = @Schema(type = "integer", example = "10")),
                    @Parameter(name = "sort", description = "Sort criteria", schema = @Schema(type = "string", example = "name,asc"))
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Genres found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            })
    ResponseEntity<Page<GenreDTO>> search(@ModelAttribute GenreFilterDTO filter,
                                          @RequestParam(defaultValue = "0") @Min(0) int page,
                                          @RequestParam(defaultValue = "10") @Min(10) @Max(100) int size,
                                          @RequestParam(defaultValue = "name,asc") String[] sort);
}