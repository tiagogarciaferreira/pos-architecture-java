package br.edu.infnet.tiago.infrastructure.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static br.edu.infnet.tiago.infrastructure.constants.SecurityConstants.OMDB_API_KEY;
import static br.edu.infnet.tiago.infrastructure.constants.SecurityConstants.OMDB_API_KEY_NAME;

@RequiredArgsConstructor
@Component
public class OmdbApiRequestInterceptor implements RequestInterceptor {

    private final Environment environment;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.query(OMDB_API_KEY_NAME, environment.getRequiredProperty(OMDB_API_KEY));
    }
}