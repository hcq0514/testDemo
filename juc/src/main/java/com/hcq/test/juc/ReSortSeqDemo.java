package com.hcq.test.juc;

import java.lang.reflect.Method;

public class ReSortSeqDemo {
    int a = 0;
    boolean flag = false;

    public void method01() {
        a = 1;           // flag = true;
                         // ----线程切换----
        flag = true;     // a = 1;
    }

    public void method02() {
        if (flag) {
            a = a + 3;
            System.out.println("a = " + a);
        }
    }

    public static void main(String[] args) {
        ReSortSeqDemo reSortSeqDemo = new ReSortSeqDemo();
        reSortSeqDemo.method01();
        reSortSeqDemo.method02();
    }

}
