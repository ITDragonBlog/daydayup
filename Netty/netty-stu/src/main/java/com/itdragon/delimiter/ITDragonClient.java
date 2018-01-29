package com.itdragon.delimiter;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;  
import io.netty.channel.ChannelFuture;  
import io.netty.channel.ChannelInitializer;  
import io.netty.channel.ChannelOption;  
import io.netty.channel.nio.NioEventLoopGroup;  
import io.netty.channel.socket.SocketChannel;  
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;  
  
public class ITDragonClient {  
      
    private static final Integer PORT = 8888;  
    private static final String HOST = "127.0.0.1";  
    private static final String DELIMITER = "_$"; // 拆包分隔符  
      
    public static void main(String[] args) {  
        NioEventLoopGroup group = new NioEventLoopGroup();  
        try {  
            Bootstrap bootstrap = new Bootstrap();  
            bootstrap.group(group)  
            .channel(NioSocketChannel.class)  
            .handler(new ChannelInitializer<SocketChannel>() {  // 设置AbstractBootstrap类里面的 handler属性
                @Override  
                protected void initChannel(SocketChannel socketChannel) throws Exception { 
                	ByteBuf delimiter = Unpooled.copiedBuffer(DELIMITER.getBytes());  
                    // 设置特殊分隔符  推荐
                    socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(128, delimiter));  
                    // 设置指定长度分割  不推荐，两者选其一
//                    socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(8));  
                    // 设置字符串形式的解码  
                    socketChannel.pipeline().addLast(new StringDecoder()); 
                    socketChannel.pipeline().addLast(new ITDragonClientHandler());  
                }  
            })  
            .option(ChannelOption.SO_KEEPALIVE, true); 
              
            ChannelFuture future = bootstrap.connect(HOST, PORT).sync(); // 建立连接  
            future.channel().writeAndFlush(Unpooled.copiedBuffer(("1+1"+DELIMITER).getBytes()));  
            future.channel().writeAndFlush(Unpooled.copiedBuffer(("6+1"+DELIMITER).getBytes()));  
//            future.channel().writeAndFlush(Unpooled.copiedBuffer("123456789".getBytes())); // 传的个数是9个，只打印了5个，还有4个在等待中  
            future.channel().closeFuture().sync();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
        	group.shutdownGracefully();  
        }  
    }  
  
}  