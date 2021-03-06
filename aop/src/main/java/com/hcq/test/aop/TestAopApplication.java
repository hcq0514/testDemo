package com.hcq.test.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication(scanBasePackages = "com.hcq.test")
@EnableAspectJAutoProxy
public class TestAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestAopApplication.class);
    }


}
