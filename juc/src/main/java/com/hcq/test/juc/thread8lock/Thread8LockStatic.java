package com.hcq.test.juc.thread8lock;

/**
 * @author : hcq
 * @date : 2019/8/14
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 题目：判断打印的 "one" or "two" ？
 * <p>
 * 1. 两个普通同步方法，两个线程，标准打印， 打印? //one  two
 * 2. 新增 Thread.sleep() 给 getOne() ,打印? //one  two
 * 3. 新增普通方法 getThree() , 打印? //three  one   two
 * 4. 两个普通同步方法，两个 Number 对象，打印?  //two  one
 * 5. 修改 getOne() 为静态同步方法，打印?  //two   one
 * 6. 修改两个方法均为静态同步方法，一个 Number 对象?  //one   two
 * 7. 一个静态同步方法，一个非静态同步方法，两个 Number 对象?  //two  one
 * 8. 两个静态同步方法，两个 Number 对象?   //one  two
 * <p>
 * 线程八锁的关键：
 * ①非静态方法的锁默认为  this,  静态方法的锁为 对应的 Class 实例
 * ②某一个时刻内，只能有一个线程持有锁，无论几个方法。
 */
//         * 6. 修改两个方法均为静态同步方法，一个 Number 对象?  //one   two
//         * 7. 一个静态同步方法，一个非静态同步方法，两个 Number 对象?  //two  one
//         * 8. 两个静态同步方法，两个 Number 对象?   //one  two


//     * 5. 修改 getOne() 为静态同步方法，打印?  //two   one
//public class Thread8LockStatic {
//    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        Number number2 = new Number();
//        Runnable runnable1 = Number::getOne;
//        Runnable runnable2 = number2::getTwo;
//        executorService.submit(runnable1);
//        executorService.submit(runnable2);
//    }
//
//}
//
//class Number {
//    public static synchronized void getOne() {
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("one");
//    }
//
//    synchronized void getTwo() {
//        System.out.println("two");
//    }
//
//    修改两个方法均为静态同步方法，一个 Number 对象?  //one   two
public class Thread8LockStatic {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Runnable runnable1 = Number::getOne;
        Runnable runnable2 = Number::getTwo;
        executorService.submit(runnable1);
        executorService.submit(runnable2);
    }

}

class Number {
    static synchronized void getOne() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }

    static synchronized void getTwo() {
        System.out.println("two");
    }
}
