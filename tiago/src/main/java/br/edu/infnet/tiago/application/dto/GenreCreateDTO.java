package br.edu.infnet.tiago.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenreCreateDTO {

    @NotBlank(message = "Genre name is required")
    @Size(min = 4, max = 50, message = "Genre name must be between 4 and 50 characters")
    private String name;
}