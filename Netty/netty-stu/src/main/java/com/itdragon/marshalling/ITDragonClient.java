package com.itdragon.marshalling;

import java.io.File;
import java.io.FileInputStream;
import com.itdragon.utils.ITDragonUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ITDragonClient {
	
	private static final int PORT = 8888;  
    private static final String HOST = "127.0.0.1";  
      
    public static void main(String[] args) {  
        EventLoopGroup workerGroup = new NioEventLoopGroup();  
        try {  
            Bootstrap bootstrap = new Bootstrap();  
            bootstrap.group(workerGroup)  
            .channel(NioSocketChannel.class)  
            .handler(new ChannelInitializer<SocketChannel>() {  
                @Override  
                protected void initChannel(SocketChannel socketChannel) throws Exception {  
                    socketChannel.pipeline().addLast(ITDragonMarshallerFactory.buildMarshallingDecoder()); 	// 配置编码器  
                    socketChannel.pipeline().addLast(ITDragonMarshallerFactory.builMarshallingEncoder()); 	// 配置解码器  
                    socketChannel.pipeline().addLast(new ITDragonClientHandler());  
                }  
            })  
            .option(ChannelOption.SO_BACKLOG, 128);  
              
            ChannelFuture future = bootstrap.connect(HOST, PORT).sync();  
            // 给服务端发送数据
            ITDragonReqData reqData = new ITDragonReqData();  
            reqData.setId(1L);  
            reqData.setName("ITDragon博客");  
            reqData.setRequestMsg("这是一篇Netty的编解码博客!");  
            // 指定附件的路径  System.getProperty("user.dir") --》 当前程序所在目录  File.separatorChar --》会根据操作系统选择自动选择 / 或者 \)  
            String path = System.getProperty("user.dir") + File.separatorChar + "sources" +  File.separatorChar + "001.jpg";  
            File file = new File(path);  
            FileInputStream inputStream = new FileInputStream(file);  
            byte[] data = new byte[inputStream.available()];  
            inputStream.read(data);  
            inputStream.close();  
            reqData.setAttachment(ITDragonUtil.gzip(data));  
            future.channel().writeAndFlush(reqData);  
            future.channel().closeFuture().sync();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            workerGroup.shutdownGracefully();  
        }  
    }

}
