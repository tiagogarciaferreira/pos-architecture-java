package br.edu.infnet.tiago.v2.infrastructure.external.omdb;

import br.edu.infnet.tiago.infrastructure.configuration.OmdbApiRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "omdbApiClient", url = "${omdb.api.base.url}", configuration = OmdbApiRequestInterceptor.class)
public interface OmdbApiClient {

    @GetMapping(value = "/")
    OmdbMovie findByImdbId(@RequestParam("i") String imdbId);
}