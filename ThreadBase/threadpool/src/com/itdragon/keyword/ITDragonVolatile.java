package com.itdragon.keyword;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile 关键字主要作用就是使变量在多个线程中可见。
 * volatile 关键字不具备原子性，当Atomic类是具备原子性和可见性。
 * 美中不足的是多个Atomic类不具备原子性，还是需要synchronized 关键字帮忙。
 */
public class ITDragonVolatile{
	
    private volatile boolean flag = true;  
    private static volatile int count;   
    private static AtomicInteger atomicCount = new AtomicInteger(0); // 加 static 是为了避免每次实例化对象时初始值为零
    
    //	测试volatile 关键字的可见性
    private void volatileMethod() {
    	System.out.println("thread start !");  
        while (flag) {  // 如果flag为true则一直处于阻塞中，
        }  
        System.out.println("thread end !");
    }
    
    //	验证volatile 关键字不具备原子性
    private int volatileCountMethod() {
    	for (int i = 0; i < 10; i++) {
    		// 第一个线程还未将count加到10的时候，就可能被另一个线程开始修改。可能会导致最后一次打印的值不是1000
        	count++ ;	
        }  
        return count;
    }
    
    //	验证Atomic类具有原子性
    private int atomicCountMethod() {
    	for (int i = 0; i < 10; i++) {  
    		atomicCount.incrementAndGet();  
    	}  
    	// 若最后一次打印为1000则表示具备原子性，中间打印的信息可能是受println延迟影响。
    	return atomicCount.get();// 若最后一次打印为1000则表示具备原子性
    }
    
    // 验证多个 Atomic类操作不具备原子性，加synchronized关键字修饰即可
    private synchronized int multiAtomicMethod(){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		atomicCount.addAndGet(1);
		atomicCount.addAndGet(2);
		atomicCount.addAndGet(3);
		atomicCount.addAndGet(4); 
		return atomicCount.get(); //若具备原子性，则返回的结果一定都是10的倍数，需多次运行才能看到结果
    }
  
    /**
     * volatile 关键字解读
     * 这里有两个线程	：一个是main的主线程，一个是thread的子线程
     * jdk线程工作流程	：为了提高效率，每个线程都有一个工作内存，将主内存的变量拷贝一份到工作内存中。线程的执行引擎就直接从工作内存中获取变量。
     * So 问题来了		：thread线程用的是自己的工作内存，主线程将变量修改后，thread线程不知道。这就是数据不可见的问题。
     * 解决方法		：变量用volatile 关键字修饰后，线程的执行引擎就直接从主内存中获取变量。
     * 
     */
    public static void main(String[] args) throws InterruptedException {  
//    	测试volatile 关键字的可见性
    	/*ITDragonVolatile itDragonVolatile = new ITDragonVolatile();  
    	Thread thread = new Thread(itDragonVolatile);
    	thread.start();
    	Thread.sleep(1000);  // 等线程启动了，再设置值
    	itDragonVolatile.setFlag(false);  
    	System.out.println("flag : " + itDragonVolatile.isFlag());*/  
    	
//    	验证volatile 关键字不具备原子性 和 Atomic类具有原子性
    	final ITDragonVolatile itDragonVolatile = new ITDragonVolatile();
		List<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < 100; i++) {
			threads.add(new Thread(new Runnable() {
				@Override
				public void run() {
					// 中间打印的信息可能是受println延迟影响，请看最后一次打印的结果
					System.out.println(itDragonVolatile.multiAtomicMethod());
				}
			}));
		}
		for(Thread thread : threads){
			thread.start();
		}
    }  
      
    public boolean isFlag() {  
        return flag;  
    }  
  
    public void setFlag(boolean flag) {  
        this.flag = flag;  
    }  

}
