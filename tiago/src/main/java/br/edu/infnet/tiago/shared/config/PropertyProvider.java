package br.edu.infnet.tiago.shared.config;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PropertyProvider {

    private final Environment environment;

    public String getOmdbApiKey() {
        return environment.getProperty("omdb.api.key", "");
    }
}