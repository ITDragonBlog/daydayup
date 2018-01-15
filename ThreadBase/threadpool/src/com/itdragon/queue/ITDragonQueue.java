package com.itdragon.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ITDragonQueue {
	
	/**
	 * ArrayBlockingQueue : 基于数组的阻塞队列实现，在内部维护了一个定长数组，以便缓存队列中的数据对象。
	 * 内部没有实现读写分离，生产和消费不能完全并行，
	 * 长度是需要定义的，
	 * 可以指定先进先出或者先进后出，
	 * 是一个有界队列。
	 * @throws Exception
	 */
	@Test
	public void ITDragonArrayBlockingQueue() throws Exception {  
        ArrayBlockingQueue<String> array = new ArrayBlockingQueue<String>(5); // 可以尝试 队列长度由3改到5  
        array.offer("offer 插入数据方法---成功返回true 否则返回false");  
        array.offer("offer 3秒后插入数据方法", 3, TimeUnit.SECONDS);  
        array.put("put 插入数据方法---但超出队列长度则阻塞等待，没有返回值");  
        array.add("add 插入数据方法---但超出队列长度则提示 java.lang.IllegalStateException"); //  java.lang.IllegalStateException: Queue full  
        System.out.println(array);
        System.out.println(array.take() + " \t还剩元素 : " + array);   // 从头部取出元素，并从队列里删除，若队列为null则一直等待
        System.out.println(array.poll() + " \t还剩元素 : " + array);   // 从头部取出元素，并从队列里删除，执行poll 后 元素减少一个
        System.out.println(array.peek() + " \t还剩元素 : " + array);   // 从头部取出元素，执行peek 不移除元素
    }
	
	@Test
	public void ITDragonLinkedBlockingQueue() throws Exception {
		
	}

}
