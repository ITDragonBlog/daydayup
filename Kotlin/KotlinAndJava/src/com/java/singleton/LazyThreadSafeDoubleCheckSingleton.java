package com.java.singleton;

/**
 * 双重检查懒汉模式
 * 在synchronized 加锁基础上通过volatile 可见性关键字，避免多个线程重复加锁
 */
public class LazyThreadSafeDoubleCheckSingleton {

    private static volatile LazyThreadSafeDoubleCheckSingleton instance;

    private LazyThreadSafeDoubleCheckSingleton(){}

    public static LazyThreadSafeDoubleCheckSingleton getInstance(){
        if(instance == null){
            synchronized (LazyThreadSafeDoubleCheckSingleton.class){
                if(instance == null) {
                    instance = new LazyThreadSafeDoubleCheckSingleton();
                }
            }
        }
        return instance;
    }

}
