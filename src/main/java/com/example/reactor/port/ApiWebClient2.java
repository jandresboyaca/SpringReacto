
package com.example.reactor.port;

import com.example.reactor.exceptions.MyCustomException;
import com.example.reactor.dto.ChildResourceDTO;
import com.example.reactor.dto.ResourceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
/*
 * {@link https://www.woolha.com/tutorials/project-reactor-error-handling-examples}
 */
public class ApiWebClient2 {


    private final WebClient webClientError = WebClient.builder()
            .baseUrl("https://63abb934cf281dba8c28cc1d.mockapi.io/resource/x")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    public Mono<? extends ResourceDTO> test(Integer id) {
        return webClientError.get()
                .retrieve()
                .onStatus(HttpStatus.INTERNAL_SERVER_ERROR::equals, clientResponse -> clientResponse.bodyToMono(String.class).flatMap(s -> Mono.error(new MyCustomException(s))))
                .bodyToMono(ChildResourceDTO.class)
                .doOnError(throwable -> log.error(throwable.getMessage()))
                .onErrorResume(MyCustomException.class, e -> Mono.just(ChildResourceDTO.builder().status(false).build()));

    }


}
