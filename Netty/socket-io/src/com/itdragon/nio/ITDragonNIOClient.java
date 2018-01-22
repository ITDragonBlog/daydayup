package com.itdragon.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ITDragonNIOClient {
	
	private final static int PORT = 8888;  
    private final static int BUFFER_SIZE = 1024;  
    private final static String IP_ADDRESS = "127.0.0.1";  
  
    // 从代码中可以看出，和传统的IO编程很像，很大的区别在于数据是写入缓冲区  
    public static void main(String[] args) {  
    	clientReq();
    }  
    
    private static void clientReq() {
    	// 1.创建连接地址  
        InetSocketAddress inetSocketAddress = new InetSocketAddress(IP_ADDRESS, PORT);  
        // 2.声明一个连接通道  
        SocketChannel socketChannel = null;  
        // 3.创建一个缓冲区  
        ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);  
        try {  
            // 4.打开通道  
            socketChannel = SocketChannel.open();  
            // 5.连接服务器  
            socketChannel.connect(inetSocketAddress);  
            while(true){  
                // 6.定义一个字节数组，然后使用系统录入功能：  
                byte[] bytes = new byte[BUFFER_SIZE];  
                // 7.键盘输入数据  
                System.in.read(bytes);  
                // 8.把数据放到缓冲区中  
                byteBuffer.put(bytes);  
                // 9.对缓冲区进行复位  
                byteBuffer.flip();  
                // 10.写出数据  
                socketChannel.write(byteBuffer);  
                // 11.清空缓冲区数据  
                byteBuffer.clear();  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (null != socketChannel) {  
                try {  
                    socketChannel.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        } 
    }

}
