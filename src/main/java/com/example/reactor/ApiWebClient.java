package com.example.reactor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ApiWebClient {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://63abb934cf281dba8c28cc1d.mockapi.io/resource/{id}")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    public Mono<ResourceDTO> test(Integer id) {
        log.info(id.toString());
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.build(id))
                .retrieve()
                .bodyToMono(ResourceDTO.class);
    }


}
