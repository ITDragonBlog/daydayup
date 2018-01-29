package com.itdragon.delimiter;

import com.itdragon.utils.ITDragonUtil;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;  
  
/** 
 * DISCARD协议 ：丢弃协议，只接收数据，不做任何处理。 
 * ECHO服务：响应式协议，返回数据。 
 * 实现步骤： 
 * step1 继承 ChannelInboundHandlerAdapter，它已经实现了ChannelHandler接口，简化了开发。
 * step2 覆盖chanelRead()事件处理方法 ，每当服务器从客户端收到新的数据时，该方法会在收到消息时被调用。
 * step3 释放ByteBuffer，ByteBuf是一个引用计数对象，这个对象必须显示地调用release()方法来释放 
 * step4 异常处理，即当Netty由于IO错误或者处理器在处理事件时抛出的异常时。在大部分情况下，捕获的异常应该被记录下来并且把关联的channel给关闭掉。 
 * ChannelHandlerContext : ChannelPipeline并不是直接管理ChannelHandler，而是通过ChannelHandlerContext来间接管理
 */  
public class ITDragonServerHandler extends ChannelInboundHandlerAdapter{  
	
	private static final String DELIMITER = "_$"; // 拆包分隔符  
      
    @Override  
    public void channelRead(ChannelHandlerContext chc, Object msg) {  
        try {  
        	// 普通读写数据
        	/* 设置字符串形式的解码 new StringDecoder() 后可以直接使用
            ByteBuf buf = (ByteBuf) msg;  
            byte[] req = new byte[buf.readableBytes()];  
            buf.readBytes(req);  
            String body = new String(req, "utf-8");  
            */
        	System.out.println("Netty Server : " + msg.toString());
            // 分隔符拆包  
            String response = ITDragonUtil.cal(msg.toString())+ DELIMITER;  
            chc.channel().writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));  // 数据是写入到Buffer缓冲中，需要flush一下
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            ReferenceCountUtil.release(msg); // 写入方法writeAndFlush ，Netty已经释放了
        }  
    }  
      
    // 当出现Throwable对象才会被调用
    @Override  
    public void exceptionCaught(ChannelHandlerContext chc, Throwable cause) {  
        // 这个方法的处理方式会在遇到不同异常的情况下有不同的实现，比如你可能想在关闭连接之前发送一个错误码的响应消息。  
        cause.printStackTrace();  
        chc.close();  
    }  
}  
