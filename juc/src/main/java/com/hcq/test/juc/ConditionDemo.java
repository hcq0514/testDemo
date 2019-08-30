package com.hcq.test.juc;

import com.hcq.test.juc.util.ThreadUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : hcq
 * @date : 2019/8/30
 */
public class ConditionDemo {
    private AtomicInteger atomicInteger = new AtomicInteger(1);
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    void s1() {
        lock.lock();
        try {
            while (atomicInteger.get() != 1) {
                condition1.await();
            }
            System.out.println("S1执行完成-----");
            atomicInteger.getAndIncrement();
            System.out.println("唤醒S2");
            condition2.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    void s2() {
        lock.lock();
        try {
            while (atomicInteger.get() != 2) {
                condition2.await();
            }
            System.out.println("S2执行完成-----");
            atomicInteger.getAndIncrement();
            System.out.println("唤醒S3");
            condition3.signal();
            lock.unlock();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    void s3() {
        lock.lock();
        try {
            while (atomicInteger.get() != 3) {
                condition3.await();
            }
            System.out.println("S3执行完成-----");
            atomicInteger.getAndIncrement();
//            condition1.signal();
            lock.unlock();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionDemo conditionDemo = new ConditionDemo();
        ExecutorService threadPool = ThreadUtils.getThreadPool(5);
        threadPool.submit(conditionDemo::s1);
        threadPool.submit(conditionDemo::s2);
        threadPool.submit(conditionDemo::s3);
    }
}
