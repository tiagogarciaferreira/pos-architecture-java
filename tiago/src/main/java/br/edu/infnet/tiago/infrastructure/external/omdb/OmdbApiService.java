package br.edu.infnet.tiago.infrastructure.external.omdb;

import br.edu.infnet.tiago.infrastructure.exception.custom.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;
import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class OmdbApiService {

    private final OmdbApiClient omdbApiClient;

    public OmdbMovie findByImdbId(String imdbId) {
        OmdbMovie omdbMovie = omdbApiClient.findByImdbId(imdbId);
        if (isNull(omdbMovie)) throw new NotFoundException(format("ImdbId '%s' not found", imdbId));
        return omdbMovie;
    }
}