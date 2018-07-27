package space.efremov.otus.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggerAspect {

    @Around(value = "@annotation(space.efremov.otus.spring.aspect.Loggable)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        final Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

        log.debug("*** Start method {} with args: {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        final Object result = joinPoint.proceed();
        log.debug("*** End method {}", joinPoint.getSignature().getName());
        return result;
    }
}
