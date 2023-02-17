package com.example.reactor.service;

import com.example.reactor.dto.ResourceDTO;
import com.example.reactor.logAnnotation.LoggableClass;
import com.example.reactor.port.ApiWebClient2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service("SERVICE_1")
@RequiredArgsConstructor
public class CleanService implements GeneralService {

    private final ApiWebClient2 webClient;

    @Override
    @LoggableClass
    public Mono<? extends ResourceDTO> test() {
        return webClient.test(1).flatMap(resourceDTO -> {
            this.defaultMethod(resourceDTO);
            return Mono.just(resourceDTO);
        });
    }

}
