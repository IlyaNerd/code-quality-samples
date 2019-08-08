package com.itechart.error.handling.mvc;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PersonRepositoryLoggingAspect {

    @Pointcut("execution(* com.itechart.error.handling.mvc.PersonRepository.getPerson(..))")
    public void getPerson() {
    }

    @Around("getPerson() && args(name)")
    public Object logGetPerson(ProceedingJoinPoint pjp, String name) throws Throwable {
        logger(pjp).debug("Getting person with name {}", name);
        Object person = pjp.proceed(pjp.getArgs());
        logger(pjp).debug("Got person {}", person);
        return person;
    }

    private static Logger logger(JoinPoint jp) {
        return LoggerFactory.getLogger(jp.getTarget().getClass());
    }
}
