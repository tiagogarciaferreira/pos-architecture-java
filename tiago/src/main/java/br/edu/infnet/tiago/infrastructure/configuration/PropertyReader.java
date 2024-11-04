package br.edu.infnet.tiago.infrastructure.configuration;

import org.springframework.stereotype.Component;

@Component
public class PropertyReader {

    public String getOmdbApiKey() {
        return "9da844b2";
    }
}