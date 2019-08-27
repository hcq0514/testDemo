package com.hcq.test.juc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : hcq
 * @date : 2019/8/27
 */
public class ReentrantLockDemo {
    synchronized
    public static void main(String[] args) {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        ReentrantLock reentrantLock = new ReentrantLock();
        AtomicInteger atomicInteger = new AtomicInteger(1);
        reentrantLockDemo.reentrantLock1(reentrantLock,atomicInteger);
    }

    private void reentrantLock1(ReentrantLock reentrantLock, AtomicInteger atomicInteger) {
        reentrantLock.lock();
        System.out.println("循环次数: " + atomicInteger.getAndIncrement());
        if (atomicInteger.get() > 5) {
            return;
        }
        reentrantLock1(reentrantLock, atomicInteger);
        reentrantLock.unlock();
    }
}
