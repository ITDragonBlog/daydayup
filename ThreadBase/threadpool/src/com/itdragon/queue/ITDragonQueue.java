package com.itdragon.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * 阻塞队列
 * ArrayBlockingQueue		：有界
 * LinkedBlockingQueue		：无界
 * SynchronousQueue			：无内存直接用
 * 非阻塞队列
 * ConcurrentLinkedQueue	：高性能
 */
public class ITDragonQueue {
	
	/**
	 * ArrayBlockingQueue : 基于数组的阻塞队列实现，在内部维护了一个定长数组，以便缓存队列中的数据对象。
	 * 内部没有实现读写分离，生产和消费不能完全并行，
	 * 长度是需要定义的，
	 * 可以指定先进先出或者先进后出，
	 * 是一个有界队列。
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
	
	/**
	 * LinkedBlockingQueue：基于列表的阻塞队列，在内部维护了一个数据缓冲队列（该队列由一个链表构成）。
	 * 其内部实现采用读写分离锁，能高效的处理并发数据，生产者和消费者操作的完全并行运行
	 * 可以不指定长度，
	 * 是一个无界队列。
	 */
	@Test
	public void ITDragonLinkedBlockingQueue() throws Exception {
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		queue.offer("1.无界队列");
		queue.add("2.语法和ArrayBlockingQueue差不多");
		queue.put("3.实现采用读写分离");
		List<String> list = new ArrayList<String>();
		System.out.println("返回截取的长度 : " + queue.drainTo(list, 2));
		System.out.println("list : " + list);
	}
	
	/**
	 * SynchronousQueue：没有缓冲的队列，生存者生产的数据直接会被消费者获取并消费。
	 */
	@Test
	public void ITDragonSynchronousQueue() throws Exception {
		final SynchronousQueue<String> queue = new SynchronousQueue<String>();
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("take , 在没有取到值之前一直处理阻塞  : " + queue.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread1.start();
		Thread.sleep(2000);
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				queue.add("进值!!!");
			}
		});
		thread2.start();	
	}

	/**
	 * ConcurrentLinkedQueue：是一个适合高并发场景下的队列，通过无锁的方式，实现了高并发状态下的高性能，性能好于BlockingQueue。
	 * 它是一个基于链接节点的无界限线程安全队列。该队列的元素遵循先进先出的原则。头是最先加入的，尾是最后加入的，不允许null元素。
	 * 无阻塞队列，没有 put 和 take 方法
	 */
	@Test
	public void ITDragonConcurrentLinkedQueue() throws Exception {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();  
        queue.offer("1.高性能无阻塞");
		queue.add("2.无界队列");
		System.out.println(queue);
        System.out.println(queue.poll() + " \t  : " + queue);   // 从头部取出元素，并从队列里删除，执行poll 后 元素减少一个
        System.out.println(queue.peek() + " \t  : " + queue);   // 从头部取出元素，执行peek 不移除元素
	}
	
}
