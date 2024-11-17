package br.edu.infnet.tiago.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DirectorDTO {

    private Long id;

    private String name;

    private LocalDate dateOfBirth;

    private int age;

    private CountryDTO country;
}