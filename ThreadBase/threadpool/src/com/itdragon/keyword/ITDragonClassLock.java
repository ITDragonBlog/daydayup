package com.itdragon.keyword;

public class ITDragonClassLock {
	
	/**
	 * 类锁：static + synchronized 修饰则表示类锁，打印的结果是 thread1 线程先执行完，然后在执行thread2线程。
	 * 若没有被static 修饰，则thread1 和 thread2 几乎同时执行，同时结束
	 */
	private synchronized void classLock(String args) {
		System.out.println(args + "start......");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(args + "end......");
	}
	
	public static void main(String[] args) {
		ITDragonClassLock classLock1 = new ITDragonClassLock();
		ITDragonClassLock classLock2 = new ITDragonClassLock();
		
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				classLock1.classLock("classLock1");
			}
		});
		thread1.start();
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				classLock2.classLock("classLock2");
			}
		});
		thread2.start();
	}
	
}
