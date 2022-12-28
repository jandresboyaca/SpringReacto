package com.example.reactor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CleanService {


    public void test(List<ResourceDTO> resourceDTOS) {
        log.info(resourceDTOS.toString());
    }

}
