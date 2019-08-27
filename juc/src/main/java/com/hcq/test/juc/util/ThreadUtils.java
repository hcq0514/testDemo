package com.hcq.test.juc.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : hcq
 * @date : 2019/8/27
 */
public class ThreadUtils {
    public static ExecutorService getThreadPool(int size) {
        return Executors.newFixedThreadPool(size);
    }
}
