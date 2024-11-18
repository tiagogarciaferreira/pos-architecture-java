package br.edu.infnet.tiago.infrastructure.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static br.edu.infnet.tiago.infrastructure.constants.SecurityConstants.OMDB_API_KEY;

@RequiredArgsConstructor
@Component
public class OmdbApiRequestInterceptor implements RequestInterceptor {

    private final Environment environment;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.query("apikey", environment.getRequiredProperty(OMDB_API_KEY));
    }
}