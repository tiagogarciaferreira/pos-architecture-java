package br.edu.infnet.tiago.v2.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudioCreateDTO {

    @NotBlank(message = "Studio name is required")
    @Size(min = 3, max = 50, message = "Studio name must be between 3 and 50 characters")
    private String name;

    @NotNull(message = "Country ID cannot be null")
    @Min(value = 1, message = "Country ID must be greater than zero")
    private Long countryId;
}