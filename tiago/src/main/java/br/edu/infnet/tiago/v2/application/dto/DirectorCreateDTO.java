package br.edu.infnet.tiago.v2.application.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DirectorCreateDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @NotNull(message = "Date of Birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotNull(message = "Country ID cannot be null")
    @Min(value = 1, message = "Country ID must be greater than zero")
    private Long countryId;

    @Size(max = 100, message = "The list of awards can contain a maximum of 100 items")
    private List<String> awards;
}