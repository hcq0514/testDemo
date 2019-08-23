package com.hcq.test.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author : hcq
 * @date : 2019/8/23
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
//        让一些线程堵塞直到另一个线程完成一系列操作后才被唤醒。
//        CountDownLatch 主要有两个方法，当一个或多个线程调用 await 方法时，调用线程会被堵塞，
//        其他线程调用 countDown 方法会将计数减一（调用 countDown 方法的线程不会堵塞），
//        当计数其值变为零时，因调用 await 方法被堵塞的线程会被唤醒，继续执行。
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "离开了教室");
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("管理员关闭了班级门");
    }
}
