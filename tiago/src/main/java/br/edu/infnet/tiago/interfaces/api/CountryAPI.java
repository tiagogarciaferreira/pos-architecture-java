package br.edu.infnet.tiago.interfaces.api;

import br.edu.infnet.tiago.application.dto.CountryCreateDTO;
import br.edu.infnet.tiago.application.dto.CountryDTO;
import br.edu.infnet.tiago.application.dto.CountryFullDTO;
import br.edu.infnet.tiago.application.dto.CountryUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.CountryFilterDTO;
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

public interface CountryAPI {

    @Operation(summary = "Create a new country", description = "Creates a new country in the system.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the country to be created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CountryCreateDTO.class))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Country created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CountryDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
            })
    ResponseEntity<CountryDTO> create(@RequestBody CountryCreateDTO countryCreateDTO);

    @Operation(summary = "Get country by ID", description = "Fetches a country by its ID.",
            parameters = {@Parameter(name = "id", description = "ID of the country", required = true, schema = @Schema(type = "integer", example = "1"))},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Country retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CountryFullDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Country not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
            })
    ResponseEntity<CountryFullDTO> getById(@PathVariable Long id);

    @Operation(summary = "Update country", description = "Updates an existing country's details.",
            parameters = {@Parameter(name = "id", description = "ID of the country to update", required = true, schema = @Schema(type = "integer", example = "1"))},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the country to be updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CountryUpdateDTO.class))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Country updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CountryDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
                    @ApiResponse(responseCode = "404", description = "Country not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
            })
    ResponseEntity<CountryDTO> update(@PathVariable Long id, @RequestBody CountryUpdateDTO countryUpdateDTO);

    @Operation(summary = "Delete country", description = "Deletes a country by its ID.",
            parameters = {@Parameter(name = "id", description = "ID of the country to delete", required = true, schema = @Schema(type = "integer", example = "1"))},
            responses = {
                    @ApiResponse(responseCode = "204", description = "Country deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Country not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
            })
    ResponseEntity<Void> delete(@PathVariable Long id);

    @Operation(summary = "Search countries", description = "Search for countries based on the given filters and pagination options.",
            parameters = {
                    @Parameter(name = "filter", description = "Filter criteria for country search", schema = @Schema(implementation = CountryFilterDTO.class)),
                    @Parameter(name = "page", description = "Page number", schema = @Schema(type = "integer", example = "0")),
                    @Parameter(name = "size", description = "Page size", schema = @Schema(type = "integer", example = "10")),
                    @Parameter(name = "sort", description = "Sort criteria", schema = @Schema(type = "string", example = "name,asc"))
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "countries found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
            })
    ResponseEntity<Page<CountryDTO>> search(@ModelAttribute CountryFilterDTO filter,
                                            @RequestParam(defaultValue = "0") @Min(0) int page,
                                            @RequestParam(defaultValue = "10") @Min(10) @Max(100) int size,
                                            @RequestParam(defaultValue = "name,asc") String[] sort);
}