package br.edu.infnet.tiago.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DirectorFullDTO {

    private Long id;

    private String name;

    private LocalDate dateOfBirth;

    private int age;

    private CountryDTO country;

    private List<String> awards;

    private List<MovieDTO> movies;

    private OffsetDateTime created;

    private OffsetDateTime modified;
}