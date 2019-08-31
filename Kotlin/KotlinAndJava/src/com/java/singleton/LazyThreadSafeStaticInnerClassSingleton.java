package com.java.singleton;

/**
 * 静态内部类-懒汉模式
 * 通过JVM对类加载的线程安全，推荐使用
 */
public class LazyThreadSafeStaticInnerClassSingleton {

    private LazyThreadSafeStaticInnerClassSingleton(){}

    public static LazyThreadSafeStaticInnerClassSingleton getInstance(){
        return Holder.instance;
    }

    private static class Holder{
        private static LazyThreadSafeStaticInnerClassSingleton instance = new LazyThreadSafeStaticInnerClassSingleton();
    }
}
