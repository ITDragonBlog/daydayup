package com.itdragon.queue;

import java.util.LinkedList;  
import java.util.concurrent.atomic.AtomicInteger;

/**
 * synchronized 可以在任意对象及方法上加锁，而加锁的这段代码称为"互斥区"或"临界区"，一般给代码块加锁，通过减小锁的粒度从而提高性能。
 * 
 * Atomic* 是为了弥补volatile关键字不具备原子性的问题。虽然一个Atomic*对象是具备原子性的，但不能确保多个Atomic*对象也具备原子性。
 * 
 * volatile 关键字不具备synchronized关键字的原子性其主要作用就是使变量在多个线程中可见。
 * 
 * wait / notify
 * wait() 使线程阻塞运行，notify() 随机唤醒等待队列中等待同一共享资源的一个线程继续运行，notifyAll() 唤醒所有等待队列中等待同一共享资源的线程继续运行。
 * 1）wait 和 notify 必须要配合 synchronized 关键字使用
 * 2）wait方法是释放锁的， notify方法不释放锁
 */
public class ITDragonMyQueue {
	
	//1 需要一个承装元素的集合   
    private LinkedList<Object> list = new LinkedList<Object>();  
    //2 需要一个计数器 AtomicInteger (保证原子性和可见性)
    private AtomicInteger count = new AtomicInteger(0);  
    //3 需要制定上限和下限  
    private final Integer minSize = 0;  
    private final Integer maxSize ;  
      
    //4 构造方法  
    public ITDragonMyQueue(Integer size){  
        this.maxSize = size;  
    }  
      
    //5 初始化一个对象 用于加锁  
    private final Object lock = new Object();  
      
    //put(anObject): 把anObject加到BlockingQueue里,如果BlockQueue没有空间,则调用此方法的线程被阻断，直到BlockingQueue里面有空间再继续.  
    public void put(Object obj){  
        synchronized (lock) {  
            while(count.get() == this.maxSize){  
                try {  
                    lock.wait();  		// 当Queue没有空间时，线程被阻塞 ，这里为了区分，命名为wait1
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
            }  
            list.add(obj);  			//1 加入元素  
            count.incrementAndGet();  	//2 计数器累加  
            lock.notify();  			//3 新增元素后，通知另外一个线程wait2，队列多了一个元素，可以做移除操作了。 
            System.out.println("新加入的元素为: " + obj);  
        }  
    }  
      
    //take: 取走BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等待状态直到BlockingQueue有新的数据被加入.  
    public Object take(){  
        Object ret = null;  
        synchronized (lock) {  
            while(count.get() == this.minSize){  
                try {  
                    lock.wait();  		// 当Queue没有值时，线程被阻塞 ，这里为了区分，命名为wait2
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
            }  
            ret = list.removeFirst();  	//1 做移除元素操作  
            count.decrementAndGet();  	//2 计数器递减  
            lock.notify();  			//3 移除元素后，唤醒另外一个线程wait1，队列少元素了，可以再添加操作了  
        }  
        return ret;  
    }  
      
    public int getSize(){  
        return this.count.get();  
    }  
      
    public static void main(String[] args) throws Exception{  
        final ITDragonMyQueue queue = new ITDragonMyQueue(5);  
        queue.put("a");  
        queue.put("b");  
        queue.put("c");  
        queue.put("d");  
        queue.put("e");  
        System.out.println("当前容器的长度: " + queue.getSize());  
        Thread thread1 = new Thread(new Runnable() {  
            @Override  
            public void run() {  
                queue.put("f");  
                queue.put("g");  
            }  
        },"thread1");  
        Thread thread2 = new Thread(new Runnable() {  
            @Override  
            public void run() {  
                System.out.println("移除的元素为:" + queue.take());  // 移除一个元素后再进一个，而并非同时移除两个，进入两个元素。
                System.out.println("移除的元素为:" + queue.take());  
            }  
        },"thread2");  
        thread1.start();  
        Thread.sleep(2000);
        thread2.start();  
    }  
}
/*
新加入的元素为: a
新加入的元素为: b
新加入的元素为: c
新加入的元素为: d
新加入的元素为: e
当前容器的长度: 5
移除的元素为:a
新加入的元素为: f
移除的元素为:b
新加入的元素为: g
 */
