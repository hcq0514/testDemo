package com.hcq.test.juc;

import com.hcq.test.juc.util.ThreadUtils;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 *
 * @author : hcq
 * @date : 2019/8/27
 */
public class SpinLockDemo {
    public static void main(String[] args) throws Exception {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        AtomicReference atomicReference = new AtomicReference(0);
        ThreadUtils.getThreadPool(1).submit(() -> spinLockDemo.lock1(atomicReference));
        Thread.sleep(1000);
        atomicReference.compareAndSet(0, 1);
        System.out.println("修改atomicReference值为1");
    }

    public void lock1(AtomicReference atomicReference) {
        System.out.println("enter Lock1");
        //循环等待
        while (!atomicReference.compareAndSet(1, 2)) {
        }
        System.out.println("修改atomicReference值为" + atomicReference.get());
    }
}
