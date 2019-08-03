package com.bindstone.portfolio.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Aspect for logging execution of service and repository Spring components.
 */
@Aspect
@Component
public class TimingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Environment env;

    @Value("${portfolio.timing:true}")
    private boolean isTiming;

    @Pointcut("within(com.bindstone.portfolio.repository..*) || within(com.bindstone.portfolio.service..*)")
    public void timingPointcut() {
    }

    @Around("timingPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // Start Time
        long startTime = System.currentTimeMillis();
        try {

            // Continue Called Method
            Object result = joinPoint.proceed();

            // Log Time after process
            if (isTiming) {
                long endTime = System.currentTimeMillis();
                log.info("TTIME: {}.{}() {}ms", joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(), endTime - startTime);
            }

            // Return called Method value
            return result;
        } catch (IllegalArgumentException e) {

            // Log time in case of Exception
            if (isTiming) {
                long endTime = System.currentTimeMillis();
                log.info("TTIME: {}.{}() {}ms", joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(), endTime - startTime);
            }

            // Return called Method Exception
            throw e;
        }
    }
}
