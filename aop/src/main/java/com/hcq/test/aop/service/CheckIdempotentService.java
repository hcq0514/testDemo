package com.hcq.test.aop.service;

import com.hcq.test.aop.aspect.Idempotency;
import org.aspectj.lang.ProceedingJoinPoint;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : hcq
 * @date : 2020/3/24
 */
@Service
public class CheckIdempotentService {

    @Autowired
    RedissonKeyGenerator redissonKeyGenerator;

    @Autowired
    RedissonClient redissonClient;

    public void handle(ProceedingJoinPoint joinPoint, Idempotency idempotency) {
        //获取分部署锁key生成器
        RedissonKeyGenerator keyGenerator = getKeyGenerator(idempotency);
        //生成key
        String key = keyGenerator.generateKey(joinPoint);
        //用这个key来获取锁，看能否获取
        RLock lock = redissonClient.getLock(key);
        boolean b = lock.tryLock();
        try {
            if (b) {
                //调用原来方法
                this.invoke(joinPoint);

            } else {
                //报异常
            }
        } finally {
            // 解锁
            lock.unlock();
        }
    }

    private RedissonKeyGenerator getKeyGenerator(Idempotency idempotency) {
        Class<? extends RedissonKeyGenerator> aClass = idempotency.generateKeyUse();
        try {
            return aClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    //调用原来的方法
    private Object invoke(ProceedingJoinPoint joinPoint) {
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
