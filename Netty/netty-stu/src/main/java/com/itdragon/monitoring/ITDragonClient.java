package com.itdragon.monitoring;

import com.itdragon.marshalling.ITDragonMarshallerFactory;

import io.netty.bootstrap.Bootstrap;  
import io.netty.channel.ChannelFuture;  
import io.netty.channel.ChannelInitializer;  
import io.netty.channel.ChannelOption;  
import io.netty.channel.nio.NioEventLoopGroup;  
import io.netty.channel.socket.SocketChannel;  
import io.netty.channel.socket.nio.NioSocketChannel;  
  
public class ITDragonClient {  
      
    public static void main(String[] args) {  
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();  
        try {  
            Bootstrap bootstrap = new Bootstrap();  
            bootstrap.group(workerGroup)  
            .channel(NioSocketChannel.class)  
            .handler(new ChannelInitializer<SocketChannel>() {  
                @Override  
                protected void initChannel(SocketChannel socketChannel) throws Exception {  
                    socketChannel.pipeline().addLast(ITDragonMarshallerFactory.buildMarshallingDecoder());  
                    socketChannel.pipeline().addLast(ITDragonMarshallerFactory.builMarshallingEncoder());   
                    socketChannel.pipeline().addLast(new ITDragonClientHandler());  
                }  
            })  
            .option(ChannelOption.SO_KEEPALIVE, true);  
              
            ChannelFuture future = bootstrap.connect(ITDragonCoreParam.SERVER_HOST.getValue(), Integer.parseInt(ITDragonCoreParam.PORT.getValue())).sync(); // 建立连接  
            future.channel().closeFuture().sync();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            workerGroup.shutdownGracefully();  
        }  
    }  
      
}  