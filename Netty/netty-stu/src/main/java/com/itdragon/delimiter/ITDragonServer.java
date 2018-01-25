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
  
/** 
 * NioEventLoopGroup 是用来处理I/O操作的多线程事件循环器  Netty提供了许多不同的EventLoopGroup的实现用来处理不同传输协议。
 * step1 创建两个线程组分别负责接收和处理任务
 * step2 实例化NIO服务辅助类 
 * step3 配置辅助启动类，绑定两个线程组，指定通道类型，关联服务端处理任务类，设置通道的配置参数 
 * step4 辅助启动类监听端口，
 * step5 关闭资源 
 * 
 * http://blog.csdn.net/u010853261/article/details/62885626
 */  
public class ITDragonServer {  
      
    private static final Integer PORT = 8888; 					// 被监听端口号
    private static final String DELIMITER = "_$"; 				// 拆包分隔符  
      
    public static void main(String[] args) {  
        EventLoopGroup bossGroup = new NioEventLoopGroup(); 	// 用于接收进来的连接
        EventLoopGroup workerGroup = new NioEventLoopGroup(); 	// 用于处理进来的连接
        try {  
            ServerBootstrap bootstrap = new ServerBootstrap(); 	// 启动NIO服务的辅助启动类，帮助程序员快速开发，不需要自己使用Channel
            bootstrap.group(bossGroup, workerGroup) 			// 分别设置bossGroup, workerGroup 顺序不能反
            .channel(NioServerSocketChannel.class) 				// 创建Channel的工厂，默认是ReflectiveChannelFactory。使用NioServerSocketChannel通道
            .childHandler(new ChannelInitializer<SocketChannel>() {  // 设置ServerBootstrap类里面的childHandler属性
                @Override  
                protected void initChannel(SocketChannel socketChannel) throws Exception { 
                	// 获取特殊分隔符的ByteBuffer  
                    ByteBuf delimiter = Unpooled.copiedBuffer(DELIMITER.getBytes());  
                    // 设置特殊分隔符  推荐
                    socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(128, delimiter));  
                    // 设置指定长度分割  不推荐，两者选其一
//                    socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(8));  
                    // 设置字符串形式的解码  
                    socketChannel.pipeline().addLast(new StringDecoder());  
                	// ITDragonDiscardServerHandler是我们自定义的服务器处理类，负责处理连接  
                    socketChannel.pipeline().addLast(new ITDragonServerHandler());
                }  
            })  
            .option(ChannelOption.SO_BACKLOG, 128) 				// 设置tcp缓冲区  
            .childOption(ChannelOption.SO_KEEPALIVE, true); 	// 设置保持连接  
            
            ChannelFuture future = bootstrap.bind(PORT).sync(); // 绑定端口， 调用sync()方法会一直阻塞等待channel的停止
            future.channel().closeFuture().sync(); 				// 等待关闭 ，等待服务器套接字关闭
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            workerGroup.shutdownGracefully(); 					// 关闭线程组，先打开的后关闭  
            bossGroup.shutdownGracefully();  
        }  
    }  
  
}  