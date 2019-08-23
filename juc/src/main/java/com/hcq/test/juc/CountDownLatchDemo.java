package com.hcq.test.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : hcq
 * @date : 2019/8/23
 * 让一些线程堵塞直到另一个线程完成一系列操作后才被唤醒。
 * CountDownLatch 主要有两个方法，当一个或多个线程调用 await 方法时，调用线程会被堵塞，
 * 其他线程调用 countDown 方法会将计数减一（调用 countDown 方法的线程不会堵塞），
 * 当计数其值变为零时，因调用 await 方法被堵塞的线程会被唤醒，继续执行。
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //规定几步
        CountDownLatch countDownLatch = new CountDownLatch(3);
        executorService.submit(()->{
            System.out.println("胡某人饭煮好了");
            countDownLatch.countDown();
        });
        executorService.submit(()->{
            System.out.println("胡某人菜炒好了");
            countDownLatch.countDown();
        });
        executorService.submit(()->{
            System.out.println("胡某人汤煮好了");
            countDownLatch.countDown();
        });
        countDownLatch.await();
        //只有上面规定几步都执行完成了才会执行下面语句，否则继续等待
        System.out.println("黄某人出来吃饭了");
    }
}
