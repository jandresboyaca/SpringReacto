package com.example.reactor.delegate;

import com.example.reactor.dto.ChildResourceDTO;
import com.example.reactor.dto.ResourceDTO;
import com.example.reactor.service.GeneralService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class Delegate {

    private final List<GeneralService> services;

    public void run() {

        List<Mono<? extends ResourceDTO>> requests = services.stream()
                .map(GeneralService::test)
                .collect(Collectors.toList());

        Mono.zip(requests, objects -> Arrays.stream(objects).map(ChildResourceDTO.class::cast).collect(Collectors.toList()))
                .doOnSuccess(childResourceDTOS -> childResourceDTOS.forEach(resourceDTO -> log.info("{}", resourceDTO)))
                .doOnError(throwable -> log.error(throwable.getMessage()))
                .block();
    }
}
