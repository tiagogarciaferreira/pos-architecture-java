package br.edu.infnet.tiago.application.dto.filter;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DirectorFilterDTO {

    @Size(max = 100, message = "Director IDs must contain no more than 100 elements")
    private List<Long> ids;

    @Size(max = 100, message = "Name must be at most 100 characters")
    private String name;

    @Size(max = 100, message = "Country IDs must contain no more than 100 elements")
    private List<Long> countryIds;

    private LocalDate dateOfBirthFrom;

    private LocalDate dateOfBirthTo;
}