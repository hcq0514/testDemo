package com.hcq.test.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author : hcq
 * @date : 2019/8/23
 * semaphore    [ˈseməfɔːr] 信号标; 旗语;
 * 一次只允许几个线程进入
 */


public class SemaphoreDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到了车位");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "离开了车位");
                    semaphore.release();
                }
            });

        }
    }
}
