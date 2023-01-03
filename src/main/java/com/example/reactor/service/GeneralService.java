package com.example.reactor.service;

import com.example.reactor.dto.ResourceDTO;
import reactor.core.publisher.Mono;

public interface GeneralService {


    default void defaultMethod(ResourceDTO resource) {
        resource.setChanged(true);
    }

    Mono<? extends ResourceDTO> test();
}
