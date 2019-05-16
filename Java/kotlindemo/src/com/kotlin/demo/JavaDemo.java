package com.kotlin.demo;

import java.util.*;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class JavaDemo {
    public static void main(String[] args) {
        final SynchronousQueue<String> queue = new SynchronousQueue<String>();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 从头部取出元素，并从队列里删除，执行poll 后 元素减少一个
                    System.out.println("poll , 返回" + queue.poll() + " \t还剩元素 : " + queue);
                    // 从头部取出元素，执行peek 不移除元素
                    System.out.println("peek , 返回" + queue.peek() + " \t还剩元素 : " + queue);
                    System.out.println("take , 在没有取到值之前一直处理阻塞  : " + queue.take());
                    System.out.println("isEmpty : " + queue.isEmpty());
                    System.out.println("remove : " + queue.remove());
                    System.out.println("remainingCapacity : " + queue.remainingCapacity());
                    System.out.println("iterator : " + queue.iterator().hasNext());
                    System.out.println("peek : " + queue.peek());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 四个进栈，一个出栈。其他三个会一直处于阻塞过程
                    queue.offer("offer 插入数据方法---成功返回true 否则返回false");
                    queue.offer("offer 3秒后插入数据方法", 3, TimeUnit.SECONDS);
                    queue.put("put 插入数据方法---但超出队列长度则阻塞等待，没有返回值");
                    queue.add("add 插入数据方法---但超出队列长度则提示 java.lang.IllegalStateException: Queue full");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.start();
    }
}
