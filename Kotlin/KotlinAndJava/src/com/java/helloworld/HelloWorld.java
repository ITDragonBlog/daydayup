package com.java.helloworld;

import java.util.concurrent.PriorityBlockingQueue;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World");

        PriorityBlockingQueue<Message> queue = new PriorityBlockingQueue<>();
        for (int i = 1; i <= 10; i++) {
            long priority = (long) (2000 * Math.random() * i);
            queue.add(new Message("第 " + i + " 个被加入队列，优先级: " + priority, priority));
        }
        while(!queue.isEmpty()){
            System.out.println(queue.poll().getBody());
        }
    }
}
