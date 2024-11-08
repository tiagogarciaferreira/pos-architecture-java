package br.edu.infnet.tiago.v2.infrastructure.external.omdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OmdbMovie {

    @JsonProperty("Poster")
    private String poster;

    private String imdbRating;

    private String boxOffice;

    private String imdbVotes;
}