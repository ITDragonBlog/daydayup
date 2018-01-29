package com.itdragon.delimiter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;  
  
public class ITDragonClientHandler extends ChannelInboundHandlerAdapter{  
          
	@Override 
    public void channelRead(ChannelHandlerContext chc, Object msg) {  
        try {  
        	/* 设置字符串形式的解码 new StringDecoder() 后可以直接使用
            ByteBuf buf = (ByteBuf) msg;  
            byte[] req = new byte[buf.readableBytes()];  
            buf.readBytes(req);  
            String body = new String(req, "utf-8");  
            */
            System.out.println("Netty Client :" + msg);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            ReferenceCountUtil.release(msg);  // 若收到的消息的类型是ByteBuf需要释放，因为它是引用计数对象，这个对象必须显示地调用release()方法来释放。 
        }  
    }  
      
    public void exceptionCaught(ChannelHandlerContext chc, Throwable cause) {  
        cause.printStackTrace();  
        chc.close();  
    }  
  
}  
