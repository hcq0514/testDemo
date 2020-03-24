package com.hcq.test.aop.config;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author : hcq
 * @date : 2020/3/24
 */
@Configuration
public class RedissonClientConfig {

//    @Autowired
//    RedissonClient redissonClient;
//
//    @PostConstruct
//    public RedissonClient initRedissonClient(){
//        return Redisson.create();
//    }


}
