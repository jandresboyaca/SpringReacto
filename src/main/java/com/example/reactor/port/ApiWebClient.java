
package com.example.reactor.port;

import com.example.reactor.dto.ChildResourceDTO;
import com.example.reactor.dto.ResourceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
/*
 * {@link https://www.woolha.com/tutorials/project-reactor-error-handling-examples}
 */
public class ApiWebClient {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://63abb934cf281dba8c28cc1d.mockapi.io/resource/{id}")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();


    public Mono<? extends ResourceDTO> test(Integer id) {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder.build(id))
                .retrieve()
                .bodyToMono(ChildResourceDTO.class);
    }


}
