package com.itdragon.delimiter;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;  
  
/** 
 * Netty 服务端启动流程
 * 第一步 : 创建两个线程池NioEventLoopGroup，负责接收事件和处理事件。
 * 第二步 : 设置启动类ServerBootstrap参数。
 * 第三步 : 端口绑定，并触发active事件，根据第二步的配置参数启动服务。
 * 第四步 : 关闭资源。
 * NioEventLoopGroup : 是用来处理I/O操作的多线程事件循环器。 Netty提供了许多不同的EventLoopGroup的实现用来处理不同传输协议。
 * ServerBootstrap : 启动NIO服务的辅助启动类。先配置Netty服务端启动参数，执行bind(PORT)方法才算真正启动服务。
 * group : 配置多线程事务轮询器
 * channel : 配置通道的类型。
 * handler : 服务器始化时就会执行的事件。
 * childHandler : 服务器在和客户端成功连接后会执行的事件。
 * initChannel : channelRegistered事件触发后执行，删除ChannelInitializer实例，添加该方法体中的handle。
 * option : 服务器始化的配置。
 * childOption : 服务器在和客户端成功连接后的配置。
 * bind(PORT) : 最关键的方法，绑定端口，启动服务，完成一些列配置。
 * sync() : 一直阻塞等待channel的停止。
 * SocketChannel : 继承了Channel，通过Channel可以对Socket进行各种操作。
 * ChannelHandler : 通过ChannelHandler来间接操纵Channel，简化了开发。
 * ChannelPipeline : 看成是一个ChandlerHandler的链表
 */  
public class ITDragonServer {  
      
    private static final Integer PORT = 8888; 					// 被监听端口号
    private static final String DELIMITER = "_$"; 				// 拆包分隔符  
      
    public static void main(String[] args) {  
        EventLoopGroup bossGroup = new NioEventLoopGroup(); 	// 用于接收进来的连接
        EventLoopGroup workerGroup = new NioEventLoopGroup(); 	// 用于处理进来的连接
        try {  
            ServerBootstrap serverbootstrap = new ServerBootstrap(); 	// 启动NIO服务的辅助启动类
            serverbootstrap.group(bossGroup, workerGroup) 			// 分别设置bossGroup, workerGroup 顺序不能反
            .channel(NioServerSocketChannel.class) 				// Channel的创建工厂，启动服务时会通过反射的方式来创建一个NioServerSocketChannel对象
            .handler(new LoggingHandler(LogLevel.INFO))			// handler在初始化时就会执行，可以设置打印日志级别
            .childHandler(new ChannelInitializer<SocketChannel>() {  // childHandler会在客户端成功connect后才执行，这里实例化ChannelInitializer
                @Override  
                protected void initChannel(SocketChannel socketChannel) throws Exception { 	// initChannel方法执行后删除实例ChannelInitializer，添加以下内容
                    ByteBuf delimiter = Unpooled.copiedBuffer(DELIMITER.getBytes());  		// 获取特殊分隔符的ByteBuffer
                    socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(128, delimiter)); // 设置特殊分隔符用于拆包 
//                    socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(8));  设置指定长度分割  不推荐，两者选其一
                    socketChannel.pipeline().addLast(new StringDecoder());  				// 设置字符串形式的解码  
                    socketChannel.pipeline().addLast(new ITDragonServerHandler());			// 自定义的服务器处理类，负责处理事件
                }  
            })  
            .option(ChannelOption.SO_BACKLOG, 128) 				// option在初始化时就会执行，设置tcp缓冲区  
            .childOption(ChannelOption.SO_KEEPALIVE, true); 	// childOption会在客户端成功connect后才执行，设置保持连接  
            // 用户调用方法 ServerBootstrap.bind(port) 第一步就是通过反射的方式new一个NioServerSocketChannel对象，并且在new的过程中创建了一系列的核心组件
            ChannelFuture future = serverbootstrap.bind(PORT).sync(); // 绑定端口， 阻塞等待服务器启动完成，调用sync()方法会一直阻塞等待channel的停止
            future.channel().closeFuture().sync(); 				// 等待关闭 ，等待服务器套接字关闭
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            workerGroup.shutdownGracefully(); 					// 关闭线程组，先打开的后关闭  
            bossGroup.shutdownGracefully();  
        }  
    }  
  
}  