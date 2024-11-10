package br.edu.infnet.tiago.infrastructure.interceptor;

import br.edu.infnet.tiago.infrastructure.config.PropertyReader;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OmdbApiRequestInterceptor implements RequestInterceptor {

    private final PropertyReader propertyReader;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.query("apikey", propertyReader.getOmdbApiKey());
    }
}