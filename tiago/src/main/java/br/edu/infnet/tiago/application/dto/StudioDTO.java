package br.edu.infnet.tiago.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudioDTO {

    private Long id;

    private String name;

    private CountryDTO country;
}