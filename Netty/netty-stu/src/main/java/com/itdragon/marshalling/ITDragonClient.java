package com.itdragon.marshalling;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import com.itdragon.utils.ITDragonUtil;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class ITDragonClient {
	
	private static final Integer PORT = 8888;
	private static final String HOST = "127.0.0.1";
	private EventLoopGroup group = null;
	private Bootstrap bootstrap = null;
	private ChannelFuture future = null;
	
	// 定义一个私有的静态内部类 负责初始化客户端
	private static class SingletonHolder {
		static final ITDragonClient instance = new ITDragonClient();
	}
	
	public static ITDragonClient getInstance(){
		return SingletonHolder.instance;
	}
	
	public ITDragonClient() {
		group = new NioEventLoopGroup();
		bootstrap = new Bootstrap();
		try {
			bootstrap.group(group)
			.channel(NioSocketChannel.class)
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel socketChannel) throws Exception {
					socketChannel.pipeline().addLast(ITDragonMarshallerFactory.buildMarshallingDecoder()); // 配置编码器
					socketChannel.pipeline().addLast(ITDragonMarshallerFactory.builMarshallingEncoder()); // 配置解码器
					socketChannel.pipeline().addLast(new ReadTimeoutHandler(5));
					socketChannel.pipeline().addLast(new ITDragonClientHandler());
				}
			})
			.option(ChannelOption.SO_BACKLOG, 1024);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void connect(){
		try {
			future = bootstrap.connect(HOST, PORT).sync();
			System.out.println("连接远程服务器......");				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ChannelFuture getChannelFuture(){
		if(this.future == null || !this.future.channel().isActive()){
			this.connect();
		}
		return this.future;
	}
	
	/**
	 * 特殊长连接：
	 * 1. 服务器和客户端的通道一直处于开启状态，
	 * 2. 在服务器指定时间内，没有任何通信，则断开，
	 * 3. 客户端再次向服务端发送请求则重新建立连接，
	 * 4. 从而减小服务端资源占用压力。
	 */
	public static void main(String[] args) {
		final ITDragonClient client = ITDragonClient.getInstance(); // 线程调用需要加final
		try {
			ChannelFuture future = client.getChannelFuture();
			// 1. 服务器和客户端的通道一直处于开启状态，
			for(Long i = 1L; i <= 3L; i++ ){
				ITDragonReqData reqData = new ITDragonReqData();
				reqData.setId(i);
				reqData.setName("ITDragon-" + i);
				reqData.setRequestMsg("NO." + i + " Request");
				future.channel().writeAndFlush(reqData);
				TimeUnit.SECONDS.sleep(2); // 2秒请求一次，服务器是5秒内没有请求则会断开连接
			}
			// 2. 在服务器指定时间内，没有任何通信，则断开，
			Thread.sleep(6000);
			// 3. 客户端再次向服务端发送请求则重新建立连接，
			new Thread(new Runnable() {
				public void run() {
					try {
						System.out.println("唤醒......");
						ChannelFuture cf = client.getChannelFuture();
						System.out.println("连接是否活跃  : " + cf.channel().isActive());
						System.out.println("连接是否打开  : " + cf.channel().isOpen());
						ITDragonReqData reqData = new ITDragonReqData();
						reqData.setId(4L);
						reqData.setName("ITDragon-picture");
						reqData.setRequestMsg("断开的通道被唤醒了!!!!");
						// 路径path自定义
						String path = System.getProperty("user.dir") + File.separatorChar + "sources" +  File.separatorChar + "itdragon.jpg";  
			            File file = new File(path);  
			            FileInputStream inputStream = new FileInputStream(file);  
			            byte[] data = new byte[inputStream.available()];  
			            inputStream.read(data);  
			            inputStream.close();  
			            reqData.setAttachment(ITDragonUtil.gzip(data));
						cf.channel().writeAndFlush(reqData);					
						cf.channel().closeFuture().sync();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
			future.channel().closeFuture().sync();
			System.out.println("断开连接,主线程结束.....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
/**
Netty Client active ^^^^^^
连接远程服务器......
Netty Client : ITDragonRespData [id=1, name=ITDragon-1-SUCCESS!, responseMsg=NO.1 Request-SUCCESS!]
Netty Client : ITDragonRespData [id=2, name=ITDragon-2-SUCCESS!, responseMsg=NO.2 Request-SUCCESS!]
Netty Client : ITDragonRespData [id=3, name=ITDragon-3-SUCCESS!, responseMsg=NO.3 Request-SUCCESS!]
io.netty.handler.timeout.ReadTimeoutException
断开连接,主线程结束.....
唤醒......
Netty Client active ^^^^^^
连接远程服务器......
连接是否活跃  : true
连接是否打开  : true
Netty Client : ITDragonRespData [id=4, name=ITDragon-picture-SUCCESS!, responseMsg=file upload success , file path is : D:\Users\Administrator\eclipse-workspace\netty-stu\receive\1517282274101.jpg]
*/