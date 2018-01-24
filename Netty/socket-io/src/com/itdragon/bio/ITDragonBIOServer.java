package com.itdragon.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * IO 也称为 BIO，Block IO 阻塞同步的通讯方式
 * 比较传统的技术，实际开发中基本上用Netty或者是AIO。熟悉BIO，NIO，体会其中变化的过程。作为一个web开发人员，stock通讯面试经常问题。
 * BIO最大的问题是：阻塞，同步。
 * BIO通讯方式很依赖于网络，若网速不好，阻塞时间会很长。每次请求都由程序执行并返回，这是同步的缺陷。
 * BIO工作流程：
 * 第一步：server端服务器启动
 * 第二步：server端服务器阻塞监听client请求
 * 第三步：server端服务器接收请求，创建线程实现任务
 * @author itdragon
 * 
 */
public class ITDragonBIOServer {
	
	private static final Integer PORT = 8888; // 服务器对外的端口号  
    
    public static void main(String[] args) {  
        ServerSocket server = null;  
        Socket socket = null;  
        ThreadPoolExecutor executor = null;  
        try {  
            server = new ServerSocket(PORT); // ServerSocket 启动监听端口  
            System.out.println("BIO Server 服务器启动.........");  
            /*--------------传统的新增线程处理----------------*/
            /*while (true) { 
                // 服务器监听：阻塞，等待Client请求 
                socket = server.accept(); 
                System.out.println("server 服务器确认请求 : " + socket); 
                // 服务器连接确认：确认Client请求后，创建线程执行任务  。很明显的问题，若每接收一次请求就要创建一个线程，显然是不合理的。
                new Thread(new ITDragonBIOServerHandler(socket)).start(); 
            } */
            /*--------------通过线程池处理缓解高并发给程序带来的压力（伪异步IO编程）----------------*/  
            executor = new ThreadPoolExecutor(10, 100, 1000, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(50));  
            while (true) {  
                socket = server.accept();  // 服务器监听：阻塞，等待Client请求 
                ITDragonBIOServerHandler serverHandler = new ITDragonBIOServerHandler(socket);  
                executor.execute(serverHandler);  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (null != socket) {  
                  socket.close(); 
                  socket = null;
                }  
                if (null != server) {  
                    server.close();  
                    server = null;  
                    System.out.println("BIO Server 服务器关闭了！！！！");  
                }  
                executor.shutdown();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  

}
