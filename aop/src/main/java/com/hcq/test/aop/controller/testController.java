package com.hcq.test.aop.controller;

import com.hcq.test.aop.aspect.Idempotency;
import com.hcq.test.aop.service.impl.DefaultKeyGeneratorForApp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : hcq
 * @date : 2020/3/24
 */
@RestController("test")
public class testController {

    @Idempotency(name = "name22213123",generateKeyUse = DefaultKeyGeneratorForApp.class)
    @RequestMapping("hello")
    public String testHello() {
        return "hello";
    }


}
