package com.itdragon.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * 优势，类比数据库的连接池
 * 1. 频繁的创建和销毁线程会给服务器带来很大的压力
 * 2. 若创建的线程不销毁而是留在线程池中等待下次使用，则会很大地提高效率也减轻了服务器的压力
 * 
 * workQueue 三种策略
 * 直接提交 SynchronousQueue
 * 无界队列 LinkedBlockingQueue
 * 有界队列 ArrayBlockingQueue
 * 
 * 四种拒绝策略
 * AbortPolicy : JDK默认，超出 MAXIMUM_POOL_SIZE 放弃任务抛异常 RejectedExecutionException
 * CallerRunsPolicy : 尝试直接调用被拒绝的任务，若线程池被关闭，则丢弃任务
 * DiscardOldestPolicy : 放弃队列最前面的任务，然后重新尝试执被拒绝的任务。若线程池被关闭，则丢弃任务
 * DiscardPolicy : 放弃不能执行的任务但不抛异常
 */
public class ThreadPoolExecutorStu {
	
	// 线程池中初始线程个数
	private final static Integer CORE_POOL_SIZE = 3;
	// 线程池中允许的最大线程数
	private final static Integer MAXIMUM_POOL_SIZE = 8;
	// 当线程数大于初始线程时。终止多余的空闲线程等待新任务的最长时间
	private final static Long KEEP_ALIVE_TIME = 10L;
	// 任务缓存队列 ，即线程数大于初始线程数时先进入队列中等待，此数字可以稍微设置大点，避免线程数超过最大线程数时报错。或者直接用无界队列
	private final static ArrayBlockingQueue<Runnable> WORK_QUEUE = new ArrayBlockingQueue<Runnable>(5);
	
	public static void main(String[] args) {
		Long start = System.currentTimeMillis();
		/**
		 * ITDragonThreadPoolExecutor 耗时 1503
		 * ITDragonFixedThreadPool 耗时 505
		 * ITDragonSingleThreadExecutor 报错，
		 * ITDragonCachedThreadPool 耗时506
		 * 推荐使用自定义线程池，
		 */
		ThreadPoolExecutor threadPoolExecutor = ITDragonThreadPoolExecutor();
		for (int i = 0; i < 8; i++) {	// 执行8个任务，若超过MAXIMUM_POOL_SIZE则会报错 RejectedExecutionException
			MyRunnableTest myRunnable = new MyRunnableTest(i);
			threadPoolExecutor.execute(myRunnable);
			System.out.println("线程池中现在的线程数目是："+threadPoolExecutor.getPoolSize()+",  队列中正在等待执行的任务数量为："+  
					threadPoolExecutor.getQueue().size());
		}
		// 关掉线程池 ，并不会立即停止(停止接收外部的submit任务，等待内部任务完成后才停止)，推荐使用。 与之对应的是shutdownNow，不推荐使用
		threadPoolExecutor.shutdown();	
		try {
			// 阻塞等待30秒关掉线程池，返回true表示已经关闭。和shutdown不同，它可以接收外部任务，并且还阻塞。这里为了方便统计时间，所以选择阻塞等待关闭。
			threadPoolExecutor.awaitTermination(30, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("耗时 : " + (System.currentTimeMillis() - start));
	}
	
	// 自定义线程池，开发推荐使用
	public static ThreadPoolExecutor ITDragonThreadPoolExecutor() {
		// 构建一个，初始线程数量为3，最大线程数据为8，等待时间10分钟 ，队列长度为5 的线程池
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
				CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MINUTES, WORK_QUEUE);
		return threadPoolExecutor;
	}
	
	/**
	 * 固定大小线程池
	 * corePoolSize初始线程数和maximumPoolSize最大线程数一样，keepAliveTime参数不起作用，workQueue用的是无界阻塞队列
	 */
	public static ThreadPoolExecutor ITDragonFixedThreadPool() {
		ExecutorService executor = Executors.newFixedThreadPool(8);
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
		return threadPoolExecutor;
	}
	
	/**
	 * 单线程线程池
	 * 等价与Executors.newFixedThreadPool(1);
	 * FIXME 不知道意义何在？
	 */
	public static ThreadPoolExecutor ITDragonSingleThreadExecutor() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
		return threadPoolExecutor;
	}
	
	/**
	 * 无界线程池
	 * corePoolSize 初始线程数为零
	 * maximumPoolSize 最大线程数无穷大
	 * keepAliveTime 60秒类将没有被用到的线程终止
	 * workQueue SynchronousQueue 队列，无容量，来任务就直接新增线程
	 * 不推荐使用
	 */
	public static ThreadPoolExecutor ITDragonCachedThreadPool() {
		ExecutorService executor = Executors.newCachedThreadPool();
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
		return threadPoolExecutor;
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
			Thread.sleep(500);// 模拟执行事务需要耗时
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
