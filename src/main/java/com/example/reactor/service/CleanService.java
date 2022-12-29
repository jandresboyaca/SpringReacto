package com.example.reactor.service;

import com.example.reactor.dto.ResourceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CleanService {


    public void test(List<? extends ResourceDTO> resourceDTOS) {
        resourceDTOS.forEach(resourceDTO -> log.info("{}", resourceDTO.getStatus()));
        log.info(resourceDTOS.toString());
    }

}
