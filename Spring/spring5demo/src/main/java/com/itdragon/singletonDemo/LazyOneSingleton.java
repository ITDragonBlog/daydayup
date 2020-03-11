package main.java.com.itdragon.singletonDemo;

/**
 * 懒汉模式，只有用到的时候再实例化
 */
public class LazyOneSingleton {
    private static LazyOneSingleton instance = null;

    private LazyOneSingleton() {
    }

    public synchronized static LazyOneSingleton getInstance() {
        if (instance == null) {
            instance = new LazyOneSingleton();
        }
        return instance;
    }
}
