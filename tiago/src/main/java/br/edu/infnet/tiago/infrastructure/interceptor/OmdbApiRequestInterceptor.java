package br.edu.infnet.tiago.infrastructure.interceptor;

import br.edu.infnet.tiago.shared.config.PropertyProvider;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OmdbApiRequestInterceptor implements RequestInterceptor {

    private final PropertyProvider propertyProvider;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.query("apikey", propertyProvider.getOmdbApiKey());
    }
}