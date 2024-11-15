package br.edu.infnet.tiago.application.dto;

import br.edu.infnet.tiago.domain.model.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActorDTO {

    private Long id;

    private String name;

    private LocalDate dateOfBirth;

    private int age;

    private Country country;
}