package com.example.reactor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ApiWebClient {

    public static final int ERROR_CODE = 3;

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://63abb934cf281dba8c28cc1d.mockapi.io/resource/{id}")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    private final WebClient webClientError = WebClient.builder()
            .baseUrl("https://63abb934cf281dba8c28cc1d.mockapi.io/resource/x")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    public Mono<ResourceDTO> test(Integer id) {
        if (id == ERROR_CODE) {
            return webClientError.get()
                    .retrieve()
                    .onStatus(HttpStatus.INTERNAL_SERVER_ERROR::equals, clientResponse -> Mono.error(new MyCustomException()))
                    .bodyToMono(ResourceDTO.class)
                    .onErrorResume(MyCustomException.class, e -> Mono.just(new ResourceDTO()));

        }
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.build(id))
                .retrieve()
                .bodyToMono(ResourceDTO.class);
    }


}
