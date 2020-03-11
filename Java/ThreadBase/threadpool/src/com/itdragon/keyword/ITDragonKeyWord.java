package com.itdragon.keyword;

import org.junit.Test;


public class ITDragonKeyWord {
	
	private Integer count = 5;
	
	/**
	 * 基本用法
	 * synchronized：可以在任意对象及方法上加锁，而加锁的这段代码称为"互斥区"或"临界区"
	 * 若不加 synchronized 关键字，多个线程同时修改数据，可能出现不正确的值（多次执行程序即可看到结果）
	 * 反之，若给该方法加锁后，每个线程依次执行。
	 */
	private synchronized void methodLock() {
		System.out.println("count = " + count--);
	}
	
	@Test
	public void synchronizedMethodLock() {
		for(int i = 1; i <= 5; i++) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					methodLock();
				}
			});
			thread.start();
		}
	}
	
	public synchronized void synchronizedMethod(){
		try {
			System.out.println("同步 : " + Thread.currentThread().getName());
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void asynchronizedMethod(){
		System.out.println("异步 : " + Thread.currentThread().getName());
	}

	@Test
	public void main2() {
		
		final ITDragonKeyWord mo = new ITDragonKeyWord();
		
		/**
		 * 分析：
		 * t1线程先持有object对象的Lock锁，t2线程可以以异步的方式调用对象中的非synchronized修饰的方法
		 * t1线程先持有object对象的Lock锁，t2线程如果在这个时候调用对象中的同步（synchronized）方法则需等待，也就是同步
		 */
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				mo.synchronizedMethod();
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				mo.asynchronizedMethod();
			}
		},"t2");
		
		t1.start();
		t2.start();
		
	}
}
