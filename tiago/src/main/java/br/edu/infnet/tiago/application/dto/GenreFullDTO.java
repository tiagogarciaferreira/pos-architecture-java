package br.edu.infnet.tiago.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenreFullDTO {

    private Long id;

    private String name;

    private List<MovieDTO> movies;

    private OffsetDateTime created;

    private OffsetDateTime modified;
}