package com.itdragon.threadpool;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 若有Thread1、Thread2、Thread3、Thread4四条线程分别统计C、D、E、F四个盘的大小，所有线程都统计完毕交给Thread5线程去做汇总，应当如何实现？
 * 思考：汇总，说明要把四个线程的结果返回给第五个线程，若要线程有返回值，推荐使用callable。Thread和Runnable都没返回值
 */
public class ITDragonThreads {  
	
    public static void main(String[] args) throws Exception {  
    	// 无缓冲无界线程池
        ExecutorService executor = Executors.newFixedThreadPool(8); 
        // 相对ExecutorService，CompletionService可以更精确和简便地完成异步任务的执行
        CompletionService<Long> completion = new ExecutorCompletionService<Long>(executor);  
        CountWorker countWorker = null;  
		for (int i = 0; i < 4; i++) { // 四个线程负责统计
            countWorker = new CountWorker(i+1);  
            completion.submit(countWorker);  
        }  
		// 关闭线程池
        executor.shutdown();  
        // 主线程相当于第五个线程，用于汇总数据
        long total = 0;  
		for (int i = 0; i < 4; i++) { 
			total += completion.take().get(); 
        }  
        System.out.println(total / 1024 / 1024 / 1024 +"G");  
    }  
}  
  
class CountWorker implements Callable<Long>{  
	
    private Integer type;  
    
    public CountWorker() {
	}

	public CountWorker(Integer type) {
		this.type = type;
	}

    @Override  
    public Long call() throws Exception {  
    	ArrayList<String> paths = new ArrayList<>(Arrays.asList("c:", "d:", "e:", "f:"));
        return countDiskSpace(paths.get(type - 1));  
    }  
    
    // 统计磁盘大小
    private Long countDiskSpace (String path) {  
        File file = new File(path);  
        long totalSpace = file.getTotalSpace();  
        long freeSpace = file.getFreeSpace();  
        long usedSpace = totalSpace - freeSpace;  
        System.out.println(path + " 总空间大小 : " + totalSpace / 1024 / 1024 / 1024 + "G");  
//        System.out.println(path + " 剩余空间大小 : " + freeSpace / 1024 / 1024 / 1024 + "G");  
//        System.out.println(path + " 已用空间大小 : " + usedSpace / 1024 / 1024 / 1024 + "G");  
        return totalSpace;
    }  
    
}  