package com.hcq.test.juc;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class ContainerDemo {
    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
//        Vector<Object> objects = new Vector<>();
        //Vector里面加了锁
//        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        //线程加锁
        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();
//        在读多写少的时候推荐使用 CopeOnWriteArrayList 这个类,底层数组用volatile修饰,添加数据时用可重入锁锁住了,所以写的性能消耗大一点

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                list.add(random.nextInt(10));
//                objects.add(random.nextInt(10));
            }).start();
        }
        System.out.println("list = " + list);
    }
}
