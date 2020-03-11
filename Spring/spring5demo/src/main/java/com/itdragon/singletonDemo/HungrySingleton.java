package main.java.com.itdragon.singletonDemo;

/**
 * 饿汉模式，不管用不用，先实例化对象
 */
public class HungrySingleton {

    private HungrySingleton() {
    }

    private static final HungrySingleton instance = new HungrySingleton();

    public static HungrySingleton getInstance() {
        return instance;
    }
}
