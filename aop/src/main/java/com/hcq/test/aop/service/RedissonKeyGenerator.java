package com.hcq.test.aop.service;

import org.aspectj.lang.JoinPoint;

/**
 * @author : hcq
 * @date : 2020/3/24
 */
public interface RedissonKeyGenerator {

     String generateKey(JoinPoint joinPoint);
}
