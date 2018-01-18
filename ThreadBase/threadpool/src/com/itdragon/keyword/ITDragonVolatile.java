package com.itdragon.keyword;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile 关键字主要作用就是使变量在多个线程中可见
 *
 */
public class ITDragonVolatile implements Runnable{
	
    private volatile boolean flag = true;  
    private static volatile int count;   
    private static AtomicInteger atomicCount = new AtomicInteger(0); // 不会出现以上的情况  
    
    @Override  
    public void run() {  
//    	volatileMethod();
    	volatileAndAtomicMethod();
    }  
    
    private void volatileMethod() {
    	System.out.println("thread start !");  
        while (flag) {  // 如果flag为true则一直处于阻塞中，
        }  
        System.out.println("thread end !");
    }
    
    private synchronized void volatileAndAtomicMethod() {
    	for (int i = 0; i < 1000; i++) {  
        	count++ ;  
        	atomicCount.incrementAndGet();  
        }  
        System.out.println("count : " + count);
        System.out.println("atomicCount : " + atomicCount);
    }
  
    /**
     * 解读：
     * 这里有两个线程：一个是main的主线程，一个是thread的子线程
     * 
     * 
     */
    public static void main(String[] args) throws InterruptedException {  
//    	ITDragonVolatile itDragonVolatile = new ITDragonVolatile();  
//    	Thread thread = new Thread(itDragonVolatile);
//    	thread.start();
//    	Thread.sleep(1000);  // 等线程启动了，再设置值
//    	itDragonVolatile.setFlag(false);  
//    	System.out.println("flag : " + itDragonVolatile.isFlag());  
    	for (int i = 0; i < 10; i++) {
    		new Thread(new ITDragonVolatile()).start();
		}
    }  
      
    public boolean isFlag() {  
        return flag;  
    }  
  
    public void setFlag(boolean flag) {  
        this.flag = flag;  
    }  

}
