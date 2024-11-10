package br.edu.infnet.tiago.application.dto.filter;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LanguageFilterDTO {

    @Size(max = 100, message = "Language IDs must contain no more than 100 elements")
    private List<Long> ids;

    private String name;
}