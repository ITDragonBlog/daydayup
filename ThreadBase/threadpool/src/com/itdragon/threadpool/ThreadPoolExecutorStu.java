package com.itdragon.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorStu {
	
	// 线程池中初始线程个数
	private final static Integer CORE_POOL_SIZE = 3;
	// 线程池中允许的最大线程数
	private final static Integer MAXIMUM_POOL_SIZE = 8;
	// 当线程数大于初始线程时。终止多余的空闲线程等待新任务的最长时间
	private final static Long KEEP_ALIVE_TIME = 10L;
	// 任务缓存队列 ，即线程数大于初始线程数时先进入队列中等待，此数字可以稍微设置大点，避免线程数超过最大线程数时报错。
	private final static ArrayBlockingQueue<Runnable> WORK_QUEUE = new ArrayBlockingQueue<Runnable>(5);
	
	public static void main(String[] args) {
		// 构建一个，初始线程数量为3，最大线程数据为8，等待时间10分钟 ，队列长度为5 的线程池
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
				CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MINUTES, WORK_QUEUE);
		for (int i = 0; i < 8; i++) {	// 执行8个任务
			MyRunnableTest myRunnable = new MyRunnableTest(i);
			threadPoolExecutor.execute(myRunnable);
			System.out.println("线程池中现在的线程数目是："+threadPoolExecutor.getPoolSize()+",  队列中正在等待执行的任务数量为："+  
                    threadPoolExecutor.getQueue().size());
		}
		threadPoolExecutor.shutdown();	// 关掉线程池 
	}
	
}

class MyRunnableTest implements Runnable {
	private Integer num;	// 正在执行的任务数
	public MyRunnableTest(Integer num) {
		this.num = num;
	}
	public void run() {
		System.out.println("正在执行的MyRunnable " + num);
		try {
			Thread.sleep(4000);// 模拟执行事务需要耗时
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("MyRunnable " + num + "执行完毕");
	}
}

/**
正在执行的MyRunnable 0
线程池中现在的线程数目是：1,  队列中正在等待执行的任务数量为：0
线程池中现在的线程数目是：2,  队列中正在等待执行的任务数量为：0
线程池中现在的线程数目是：3,  队列中正在等待执行的任务数量为：0
线程池中现在的线程数目是：3,  队列中正在等待执行的任务数量为：1
线程池中现在的线程数目是：3,  队列中正在等待执行的任务数量为：2
线程池中现在的线程数目是：3,  队列中正在等待执行的任务数量为：3
线程池中现在的线程数目是：3,  队列中正在等待执行的任务数量为：4
线程池中现在的线程数目是：3,  队列中正在等待执行的任务数量为：5
正在执行的MyRunnable 1
正在执行的MyRunnable 2
MyRunnable 2执行完毕
MyRunnable 0执行完毕
正在执行的MyRunnable 3
MyRunnable 1执行完毕
正在执行的MyRunnable 5
正在执行的MyRunnable 4
MyRunnable 3执行完毕
MyRunnable 4执行完毕
正在执行的MyRunnable 7
MyRunnable 5执行完毕
正在执行的MyRunnable 6
 */
