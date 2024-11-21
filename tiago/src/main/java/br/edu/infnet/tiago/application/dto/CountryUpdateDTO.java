package br.edu.infnet.tiago.application.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryUpdateDTO {

    @NotBlank(message = "Country name is required")
    @Size(min = 3, max = 50, message = "Country name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Country code is required")
    @Size(min = 2, max = 3, message = "Country code must be 2 or 3 characters")
    @Pattern(regexp = "^[A-Z]{2,3}$", message = "Country code must be uppercase letters, 2 or 3 characters")
    private String code;

    @NotNull(message = "Version cannot be null")
    @Min(value = 0, message = "Version must be greater than or equal to zero")
    private int version;
}