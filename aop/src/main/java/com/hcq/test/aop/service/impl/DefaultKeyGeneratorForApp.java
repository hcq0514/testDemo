package com.hcq.test.aop.service.impl;

import com.hcq.test.aop.service.RedissonKeyGenerator;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;

import java.lang.reflect.Parameter;

/**
 * 默认的为 rest 模块生成幂等性分布式锁key的实现类

 * Created by shenym on 2019/12/26.
 */
@Service
public class DefaultKeyGeneratorForApp implements RedissonKeyGenerator {

    public String generate(ProceedingJoinPoint joinPoint) {
        StringBuilder sb = new StringBuilder();
        // 通过  方法签名 + 方法参数值 ==》 MD5加密后做 key
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        sb.append(methodSignature.toString());
        Parameter[] parameters = methodSignature.getMethod().getParameters();
        Object[] args = joinPoint.getArgs();
        // 排除那些大容量的参数值...
        int i = 0;
        for (Parameter parameter : parameters) {
            if (!parameter.isAnnotationPresent(RequestPart.class)) {
                sb.append(args[i]);
            }
            i++;
        }
        String result = sb.toString();

//        try {
//            result = HashUtil.MD5.md5(result);
//        } catch (HashUtil.MD5.Md5EncodingException e) {
//            e.printStackTrace();
//        }
        return result;
    }


    @Override
    public String generateKey(JoinPoint joinPoint) {
        StringBuilder sb = new StringBuilder();
        // 通过  方法签名 + 方法参数值 ==》 MD5加密后做 key
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Parameter[] parameters = methodSignature.getMethod().getParameters();
        Object[] args = joinPoint.getArgs();
        // 排除那些大容量的参数值...
        int i = 0;
        for (Parameter parameter : parameters) {
            if (!parameter.isAnnotationPresent(RequestPart.class)) {
                sb.append(args[i]);
            }
            i++;
        }
        String result = sb.toString();
        return result;
    }
}
