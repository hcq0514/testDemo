package com.hcq.test.juc;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : hcq
 * @date : 2019/8/23
 * CyclicBarrier 循环栅栏的意思,只有到达这个"栅栏"值时才会执行操作
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> System.out.println(Thread.currentThread().getName() + "车满了，开始出发..."));
        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " 开始上车...");
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
