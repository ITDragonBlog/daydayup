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

}
