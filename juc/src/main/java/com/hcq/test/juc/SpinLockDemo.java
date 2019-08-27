package com.hcq.test.juc;

import com.hcq.test.juc.util.ThreadUtils;

import java.util.concurrent.ExecutorService;
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
        ExecutorService threadPool = ThreadUtils.getThreadPool(2);
        AtomicReference atomicReference = new AtomicReference();
        threadPool.submit(() -> {
            spinLockDemo.lock(atomicReference);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unlock(atomicReference);
        });
        threadPool.submit(() -> {
            spinLockDemo.lock(atomicReference);
            spinLockDemo.unlock(atomicReference);
        });
    }

    public void lock(AtomicReference atomicReference) {
        //循环等待
        while (!atomicReference.compareAndSet(null, Thread.currentThread())) {
        }
        System.out.println("线程" + Thread.currentThread().getName() + "获取锁");
    }

    public void unlock(AtomicReference atomicReference) {
        atomicReference.compareAndSet(Thread.currentThread(), null);
        System.out.println("线程" + Thread.currentThread().getName() + "释放锁");
    }
}
