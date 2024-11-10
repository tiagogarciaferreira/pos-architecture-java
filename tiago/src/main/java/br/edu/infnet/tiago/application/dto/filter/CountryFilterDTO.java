package br.edu.infnet.tiago.application.dto.filter;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryFilterDTO {

    @Size(max = 100, message = "Country IDs must contain no more than 100 elements")
    private List<Long> ids;

    private String name;

    @Size(max = 100, message = "Country CODES must contain no more than 100 elements")
    private List<String> codes;
}