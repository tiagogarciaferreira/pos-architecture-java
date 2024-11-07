package br.edu.infnet.tiago.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryDTO {

    private Long id;

    private String name;

    private String code;

    private OffsetDateTime created;

    private OffsetDateTime modified;
}