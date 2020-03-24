package com.hcq.test.aop.aspect;

import com.hcq.test.aop.service.CheckIdempotentService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : hcq
 * @date : 2020/3/24
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    CheckIdempotentService checkIdempotentService;


    @Before("@annotation(idempotency)")
    public void loginBefore(ProceedingJoinPoint proceedingJoinPoint, Idempotency idempotency) {
        checkIdempotentService.handle(proceedingJoinPoint,idempotency);
    }


}
