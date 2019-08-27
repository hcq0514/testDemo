package com.hcq.test.juc;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author : hcq
 * @date : 2019/8/27
 * ABA问题
 * CAS 判断的条件是 expect期望值跟当前值相等时，就交换。
 * 假如两个线程同时获取一个值，一个线程T1将A值改为B值然后再改回A值，另一个线程T2执行的比较慢，
 * 再取的时候T1线程已经执行完了，查询到的还是A值，满足CAS 所以会交换，但其实已经发生了两次交换
 */
public class ABAProblem {

    public static void main(String[] args) throws Exception {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        User user = new User().setName("hcq").setAge(24);
        AtomicReference<User> atomicReference = new AtomicReference<>();
        //首先开一个线程来把这个值修改两次
        threadPool.submit(() -> {
            boolean firstCAS = atomicReference.compareAndSet(null, user);
            System.out.println(Thread.currentThread().getName() + "，第一次修改" + firstCAS + "，变量数据" + atomicReference.get());
            boolean secondCAS = atomicReference.compareAndSet(user, null);
            System.out.println(Thread.currentThread().getName() + "，第二次修改" + secondCAS + "，变量数据" + atomicReference.get());

        });
        //休眠2秒，保证前面的执行完成
        Thread.sleep(2000);
        boolean threeCAS = atomicReference.compareAndSet(null, user);
        System.out.println(Thread.currentThread().getName() + "，第三次修改" + threeCAS + "，变量数据" + atomicReference.get());
    }
}

@Data
@Accessors(chain = true)
class User {
    private String name;
    private Integer age;
}
