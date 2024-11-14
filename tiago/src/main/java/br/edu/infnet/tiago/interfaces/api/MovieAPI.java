package br.edu.infnet.tiago.interfaces.api;

import br.edu.infnet.tiago.application.dto.MovieCreateDTO;
import br.edu.infnet.tiago.application.dto.MovieDTO;
import br.edu.infnet.tiago.application.dto.MovieUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.MovieFilterDTO;
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

public interface MovieAPI {

    @Operation(summary = "Create a new movie", description = "Creates a new movie in the system.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the movie to be created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieCreateDTO.class))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Movie created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            })
    ResponseEntity<MovieDTO> create(@RequestBody MovieCreateDTO movieCreateDTO);

    @Operation(summary = "Get movie by ID", description = "Fetches a movie by its ID.",
            parameters = {@Parameter(name = "id", description = "ID of the movie", required = true, schema = @Schema(type = "integer", example = "1"))},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Movie retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Movie not found")
            })
    ResponseEntity<MovieDTO> getById(@PathVariable Long id);

    @Operation(summary = "Update movie", description = "Updates an existing movie's details.",
            parameters = {@Parameter(name = "id", description = "ID of the movie to update", required = true, schema = @Schema(type = "integer", example = "1"))},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the movie to be updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieUpdateDTO.class))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Movie updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "404", description = "Movie not found")
            })
    ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieUpdateDTO movieUpdateDTO);

    @Operation(summary = "Delete movie", description = "Deletes a movie by its ID.",
            parameters = {@Parameter(name = "id", description = "ID of the movie to delete", required = true, schema = @Schema(type = "integer", example = "1"))},
            responses = {
                    @ApiResponse(responseCode = "204", description = "Movie deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Movie not found")
            })
    ResponseEntity<Void> delete(@PathVariable Long id);

    @Operation(summary = "Search movies", description = "Search for movies based on the given filters and pagination options.",
            parameters = {
                    @Parameter(name = "filter", description = "Filter criteria for movie search", schema = @Schema(implementation = MovieFilterDTO.class)),
                    @Parameter(name = "page", description = "Page number", schema = @Schema(type = "integer", example = "0")),
                    @Parameter(name = "size", description = "Page size", schema = @Schema(type = "integer", example = "10")),
                    @Parameter(name = "sort", description = "Sort criteria", schema = @Schema(type = "string", example = "name,asc"))
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Movies found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            })
    ResponseEntity<Page<MovieDTO>> search(@ModelAttribute MovieFilterDTO filter,
                                          @RequestParam(defaultValue = "0") @Min(0) int page,
                                          @RequestParam(defaultValue = "10") @Min(10) @Max(100) int size,
                                          @RequestParam(defaultValue = "title,asc") String[] sort);
}