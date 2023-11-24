package dev.andzwp.taskservice.aop;

import dev.andzwp.taskservice.exception.IdOutOfBoundsException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TaskAdvice {

    @Before("@annotation(validRequestParam)")
    public void validParam(JoinPoint joinPoint, ValidRequestParam validRequestParam) {
        var id = (Long) joinPoint.getArgs()[0];
        var max = validRequestParam.max();
        var min = validRequestParam.min();
        var range = ". Please give a value from " + min + " to " + max;
        if (max < id)
            throw new IdOutOfBoundsException("Id is bigger than " + max + range);
        else if (min > id)
            throw new IdOutOfBoundsException("Id is less than " + min + range);

    }

}
