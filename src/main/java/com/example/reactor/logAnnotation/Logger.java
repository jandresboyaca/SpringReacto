package com.example.reactor.logAnnotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Aspect
@Component
@Slf4j
public class Logger {

    @Pointcut("@annotation(com.example.reactor.logAnnotation.LoggableReactor)")
    public void loggableMethod() {
    }

    @AfterReturning(value = "loggableMethod()", returning = "result")
    public void logMethodResult(JoinPoint joinPoint, Object result) {
        if (result instanceof Mono) {
            String nameMethod = joinPoint.getSignature().getName();
            ((Mono<?>) result).subscribe(
                    value -> log.info("[End][{}]{}", nameMethod, value),
                    error -> log.error("[End][{}]{} ", nameMethod, error),
                    () -> log.info("Mono completed emitting values")
            );
        }
    }


}