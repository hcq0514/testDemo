package com.hcq.test.juc;

import com.hcq.test.juc.util.ThreadUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author : hcq
 * @date : 2019/8/27
 */
public class ABASolution {
    public static void main(String[] args) throws Exception {

        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1, 1);
        ExecutorService threadPool = ThreadUtils.getThreadPool(2);
        //首先开一个线程来把这个值修改两次
        threadPool.submit(() -> {
            boolean firstCAS = atomicStampedReference.compareAndSet(1, 2, 1, atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "，第一次修改结果:" + firstCAS + "，变量数据" + atomicStampedReference.getReference());
            boolean secondCAS = atomicStampedReference.compareAndSet(2, 1, 2, atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "，第二次修改结果:" + secondCAS + "，变量数据" + atomicStampedReference.getReference());

        });
        //休眠2秒，保证前面的执行完成
        Thread.sleep(2000);
        boolean threeCAS = atomicStampedReference.compareAndSet(1, 2, 1, atomicStampedReference.getStamp() + 1);
        System.out.println(Thread.currentThread().getName() + "，第三次修改结果:" + threeCAS + "，变量数据" + atomicStampedReference.getReference());
    }
}
