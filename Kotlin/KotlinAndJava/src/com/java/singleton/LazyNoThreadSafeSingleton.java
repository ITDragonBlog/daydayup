package com.java.singleton;

/**
 * 懒汉模式
 * 优点：使用时才实例化，加载启动快，资源占用少，无锁效率高
 * 缺点：非线程安全
 * 和饿汉模式类似，只是在调用的时候实例化对象
 */
public class LazyNoThreadSafeSingleton {

    private static LazyNoThreadSafeSingleton instance = null;

    private LazyNoThreadSafeSingleton(){
    }

    public static LazyNoThreadSafeSingleton getInstance(){
        if (null == instance) {
            instance = new LazyNoThreadSafeSingleton();
        }
        return instance;
    }

}
