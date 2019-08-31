package com.java.singleton;

/**
 * 同步锁线程安全懒汉模式
 * 在原来LazyNoThreadSafeSingleton基础上加上了synchronized关键字
 * synchronized 锁并发性能差（据说现在优化了很多）
 */
public class LazyThreadSafeSynchronizedSingleton {
    private static LazyThreadSafeSynchronizedSingleton instance = null;

    private LazyThreadSafeSynchronizedSingleton(){
    }

    public static synchronized LazyThreadSafeSynchronizedSingleton getInstance(){
        if (null == instance) {
            instance = new LazyThreadSafeSynchronizedSingleton();
        }
        return instance;
    }
}
