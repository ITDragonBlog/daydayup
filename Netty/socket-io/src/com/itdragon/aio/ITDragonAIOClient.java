package com.itdragon.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Random;

public class ITDragonAIOClient implements Runnable{  
  
	private static Integer PORT = 8888;  
    private static String IP_ADDRESS = "127.0.0.1";
    private AsynchronousSocketChannel asynSocketChannel ;  
      
    public ITDragonAIOClient() throws Exception {  
        asynSocketChannel = AsynchronousSocketChannel.open();  // 打开通道  
    }  
      
    public void connect(){  
        asynSocketChannel.connect(new InetSocketAddress(IP_ADDRESS, PORT));  // 创建连接 和NIO一样  
    }  
      
    /**
     * 如果没有client.finishConnect();这句等待完成socket连接，可能会报异常：java.nio.channels.NotYetConnectedException 
     * 异步的才不会管你有没有连接成功，都会执行下面的代码。这里需要人为的干预
     */
    public void write(String request){  
        try {  
            asynSocketChannel.write(ByteBuffer.wrap(request.getBytes())).get();  
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);  
            asynSocketChannel.read(byteBuffer).get();  
            byteBuffer.flip();  
            byte[] respByte = new byte[byteBuffer.remaining()];  
            byteBuffer.get(respByte); // 将缓冲区的数据放入到 byte数组中  
            System.out.println(new String(respByte,"utf-8").trim());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    @Override  
    public void run() {  
        while(true){  
              
        }  
    }  
      
    public static void main(String[] args) throws Exception {  
    	for (int i = 0; i < 10; i++) {
    		ITDragonAIOClient myClient = new ITDragonAIOClient();  
    		myClient.connect();  
    		new Thread(myClient, "myClient").start(); 
    		String []operators = {"+","-","*","/"};
    		Random random = new Random(System.currentTimeMillis());  
    		String expression = random.nextInt(10)+operators[random.nextInt(4)]+(random.nextInt(10)+1);
    		myClient.write(expression);  
		}
    }  

}
