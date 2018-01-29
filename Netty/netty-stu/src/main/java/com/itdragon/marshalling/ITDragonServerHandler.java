package com.itdragon.marshalling;

import java.io.File;
import java.io.FileOutputStream;

import com.itdragon.utils.ITDragonUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;  

public class ITDragonServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Netty Server active ......");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
			// 获取客户端传来的数据，之前获取的数据类型是ByteBuf 或 String
			ITDragonReqData requestData = (ITDragonReqData) msg;
			System.out.println("Netty Server : " + requestData.toString());
			// 处理数据并返回个客户端
			ITDragonRespData responseData = new ITDragonRespData();
			responseData.setId(requestData.getId());
			responseData.setName(requestData.getName() + " 不错哦!");
			responseData.setResponseMsg(requestData.getRequestMsg() + " 你很棒棒的!");
			// 如果有附件
			if (null != requestData.getAttachment()) {
				byte[] attachment = ITDragonUtil.ungzip(requestData.getAttachment());
				String path = System.getProperty("user.dir") + File.separatorChar + "receive" + File.separatorChar + "001.jpg";
				FileOutputStream outputStream = new FileOutputStream(path);
				outputStream.write(attachment);
				outputStream.close();
				responseData.setResponseMsg("收到图片了， 图片路径是 ： " + path);
			}
			ctx.writeAndFlush(responseData);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
