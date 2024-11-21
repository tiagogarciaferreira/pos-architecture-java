package br.edu.infnet.tiago.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryFullDTO {

    private Long id;

    private String name;

    private String code;

    private List<MovieDTO> movies;

    private List<ActorDTO> actors;

    private List<DirectorDTO> directors;

    private List<StudioDTO> studios;

    private OffsetDateTime created;

    private OffsetDateTime modified;

    private int version;
}