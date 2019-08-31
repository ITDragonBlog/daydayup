package com.java.singleton;

/**
 * 饿汉模式
 * 优点：线程安全，使用没有延迟
 * 缺点：启动时即创建实例，耗资源，启动慢
 */
public class HungrySingleton {

    private static HungrySingleton instance = new HungrySingleton();

    // step1 创建私有的构造函数，控制实例化数量，确保单例
    private HungrySingleton(){
    }

    // step2 对外开发获取单例对象的方法，将已经初始化的对象返回给调用者
    public static HungrySingleton getInstance(){
        return instance;
    }
}
