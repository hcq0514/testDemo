package com.hcq.test.aop.aspect;

import com.hcq.test.aop.service.impl.DefaultKeyGeneratorForApp;
import com.hcq.test.aop.service.RedissonKeyGenerator;

import java.lang.annotation.*;

/**
 * @author : hcq
 * @date : 2020/3/24
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Idempotency {

    /**
     * 字段名称
     *
     * @return
     */
    String name() default "";

    int order() default 0;

    Class<? extends RedissonKeyGenerator> generateKeyUse() default DefaultKeyGeneratorForApp.class;


}
