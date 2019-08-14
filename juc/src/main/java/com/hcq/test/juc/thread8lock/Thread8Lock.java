package com.hcq.test.juc.thread8lock;

/**
 * @author : hcq
 * @date : 2019/8/14
 */

import java.sql.Statement;
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

public class Thread8Lock {

//1:两个普通同步方法，两个线程，标准打印， 打印? //one  two
//    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        Number number = new Number();
//        Runnable runnable1 = number::getOne;
//        Runnable runnable2 = number::getTwo;
//        executorService.submit(runnable1);
//        executorService.submit(runnable2);
//    }
//}
//
//class Number {
//
//    public synchronized void getOne() {
//        System.out.println("one");
//    }
//
//    public synchronized void getTwo() {
//        System.out.println("two");
//    }
//}

//2. 新增 Thread.sleep() 给 getOne() ,打印? //one  two
//    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        Number number = new Number();
//        Runnable runnable1 = number::getOne;
//        Runnable runnable2 = number::getTwo;
//        executorService.submit(runnable1);
//        executorService.submit(runnable2);
//    }
//}
//
//class Number {
//
//    synchronized void getOne() {
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
//}
//
// 3. 新增普通方法 getThree() , 打印? //three  one   two
//    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        Number number = new Number();
//        Runnable runnable1 = number::getOne;
//        Runnable runnable2 = number::getTwo;
//        Runnable runnable3 = number::getThree;
//        executorService.submit(runnable1);
//        executorService.submit(runnable2);
//        executorService.submit(runnable3);
//    }
//}
//
//class Number {
//
//    synchronized void getOne() {
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
//    void getThree() {
//        System.out.println("three");
//    }
//}

    // * 4. 两个普通同步方法，两个 Number 对象，打印?  //two  one
//    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        Number number = new Number();
//        Number number2 = new Number();
//        Runnable runnable1 = number::getOne;
//        Runnable runnable2 = number2::getTwo;
//        executorService.submit(runnable1);
//        executorService.submit(runnable2);
//    }
//}
//
//class Number {
//    synchronized void getOne() {
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
//}

}
