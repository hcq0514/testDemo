package com.hcq.test.juc;

/**
 * @author : hcq
 * @date : 2019/8/14
 */
public class VolatileTest {

    public static void main(String[] args) {
        test01();
//        test02();
    }

    /**
     * volatile可见性测试
     * todo 不加Thread.sleep(1000)会直接同步回主内存，待研究;
     */
    private static void test01() {
        Data data = new Data();
        new Thread(() -> {
            System.out.println("come in");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.setI(100);
            System.out.println("update complete");
        }, "t1").start();

        while (data.i == 0) {
        }
        System.out.println("data = " + data.i);
    }
    /**
     * volatile原子性测试
     */
    private static void test02() {
        Data data = new Data();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    data.addOne();
                }
            }).start();
        }
        // 默认有 main 线程和 gc 线程
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(data.i);
    }
}

class Data {
    int i = 0;
//    volatile int i = 0;

    void setI(int i) {
        this.i = i;
    }

    void addOne() {
        i++;
    }
}
