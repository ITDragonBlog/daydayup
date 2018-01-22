package com.itdragon.nio;

import java.util.Scanner;

public class ITDragonNIODoubleMain {
	
	public static void main(String[] args) throws Exception{  
        // 启动服务器  
		ITDragonNIODoubleServer.start();  
        Thread.sleep(100);  
        // 启动客户端   
        ITDragonNIODoubleClient.start();  
        while(ITDragonNIODoubleClient.sendMsg(new Scanner(System.in).nextLine()));  
    }  

}
