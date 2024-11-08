package br.edu.infnet.tiago.v2.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LanguageCreateDTO {

    @NotBlank(message = "Language name is required")
    @Size(min = 3, max = 50, message = "Language name must be between 3 and 50 characters")
    private String name;
}