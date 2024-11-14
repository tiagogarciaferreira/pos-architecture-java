package br.edu.infnet.tiago.interfaces.api;

import br.edu.infnet.tiago.application.dto.LanguageCreateDTO;
import br.edu.infnet.tiago.application.dto.LanguageDTO;
import br.edu.infnet.tiago.application.dto.LanguageUpdateDTO;
import br.edu.infnet.tiago.application.dto.filter.LanguageFilterDTO;
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

public interface LanguageAPI {

    @Operation(summary = "Create a new language", description = "Creates a new language in the system.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the language to be created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LanguageCreateDTO.class))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Language created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LanguageDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            })
    ResponseEntity<LanguageDTO> create(@RequestBody LanguageCreateDTO languageCreateDTO);

    @Operation(summary = "Get language by ID", description = "Fetches a language by its ID.",
            parameters = {@Parameter(name = "id", description = "ID of the language", required = true, schema = @Schema(type = "integer", example = "1"))},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Language retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LanguageDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Language not found")
            })
    ResponseEntity<LanguageDTO> getById(@PathVariable Long languageId);

    @Operation(summary = "Update language", description = "Updates an existing language's details.",
            parameters = {@Parameter(name = "languageId", description = "ID of the language to update", required = true, schema = @Schema(type = "integer", example = "1"))},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the language to be updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LanguageUpdateDTO.class))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Language updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LanguageDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "404", description = "Language not found")
            })
    ResponseEntity<LanguageDTO> update(@PathVariable Long languageId, @RequestBody LanguageUpdateDTO languageUpdateDTO);

    @Operation(summary = "Delete language", description = "Deletes a language by its ID.",
            parameters = {@Parameter(name = "languageId", description = "ID of the language to delete", required = true, schema = @Schema(type = "integer", example = "1"))},
            responses = {
                    @ApiResponse(responseCode = "204", description = "Language deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Language not found")
            })
    ResponseEntity<Void> delete(@PathVariable Long languageId);

    @Operation(summary = "Search languages", description = "Search for languages based on the given filters and pagination options.",
            parameters = {
                    @Parameter(name = "filter", description = "Filter criteria for language search", schema = @Schema(implementation = LanguageFilterDTO.class)),
                    @Parameter(name = "page", description = "Page number", schema = @Schema(type = "integer", example = "0")),
                    @Parameter(name = "size", description = "Page size", schema = @Schema(type = "integer", example = "10")),
                    @Parameter(name = "sort", description = "Sort criteria", schema = @Schema(type = "string", example = "name,asc"))
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Languages found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            })
    ResponseEntity<Page<LanguageDTO>> search(@ModelAttribute LanguageFilterDTO filter,
                                             @RequestParam(defaultValue = "0") @Min(0) int page,
                                             @RequestParam(defaultValue = "10") @Min(10) @Max(100) int size,
                                             @RequestParam(defaultValue = "name,asc") String[] sort);
}