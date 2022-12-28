package com.example.reactor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class CleanService {

    public void test(List<ResourceDTO> resourceDTOS) {
        log.info(resourceDTOS.toString());
    }

    public void test(ResourceDTO[] resourceDTOS) {
        log.info(Arrays.toString(resourceDTOS));
    }

    public void test(Object[] objects) {
        log.info(Arrays.toString(objects));
    }
}
