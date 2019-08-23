package com.hcq.test.juc;

import java.util.concurrent.locks.ReentrantLock;

public class Count {
    //    NotReentrantLock lock = new NotReentrantLock();
    ReentrantLock lock = new ReentrantLock();

    public void print() throws InterruptedException {
        lock.lock();
        doAdd();
        lock.unlock();
    }

    private void doAdd() throws InterruptedException {
        lock.lock();
        // do something
        System.out.println("ReentrantLock");
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        Count count = new Count();
        count.print();
    }
}
