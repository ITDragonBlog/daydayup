package com.itdragon.monitoring;

import java.util.HashMap;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ITDragonServerHandler extends ChannelInboundHandlerAdapter {

	// 令牌验证的map，key为ip地址，value为密钥
	private static HashMap<String, String> authMap = new HashMap<String, String>();
	// 模拟数据库查询
	static {
		authMap.put("xxx.xxx.x.x", "xxx");
		authMap.put(ITDragonCoreParam.CLIENT_HOST.getValue(), ITDragonCoreParam.SALT_KEY.getValue());
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Netty Server Monitoring.......");
	}

	// 模拟api请求前的验证
	private boolean auth(ChannelHandlerContext ctx, Object msg) {
		System.out.println("令牌验证...............");
		String[] ret = ((String) msg).split(",");
		String clientIp = ret[0]; 	// 客户端ip地址
		String saltKey = ret[1]; 	// 数据库保存的客户端密钥
		String auth = authMap.get(clientIp); // 客户端传来的密钥
		if (null != auth && auth.equals(saltKey)) {
			ctx.writeAndFlush(ITDragonCoreParam.AUTH_SUCCESS.getValue());
			return true;
		} else {
			ctx.writeAndFlush(ITDragonCoreParam.AUTH_ERROR.getValue()).addListener(ChannelFutureListener.CLOSE);
			return false;
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// 如果传来的消息是字符串，则先验证
		if (msg instanceof String) {
			auth(ctx, msg);
		} else if (msg instanceof ITDragonRequestInfo) {
			ITDragonRequestInfo info = (ITDragonRequestInfo) msg;
			System.out.println("--------------------------------------------");
			System.out.println("当前主机ip为: " + info.getIp());
			HashMap<String, Object> cpu = info.getCpuPercMap();
			System.out.println("cpu 总使用率: " + cpu.get(ITDragonCoreParam.COMBINED.getValue()));
			System.out.println("cpu 用户使用率: " + cpu.get(ITDragonCoreParam.USER.getValue()));
			System.out.println("cpu 系统使用率: " + cpu.get(ITDragonCoreParam.SYS.getValue()));
			System.out.println("cpu 等待率: " + cpu.get(ITDragonCoreParam.WAIT.getValue()));
			System.out.println("cpu 空闲率: " + cpu.get(ITDragonCoreParam.IDLE.getValue()));

			HashMap<String, Object> memory = info.getMemoryMap();
			System.out.println("内存总量: " + memory.get(ITDragonCoreParam.TOTAL.getValue()));
			System.out.println("当前内存使用量: " + memory.get(ITDragonCoreParam.USED.getValue()));
			System.out.println("当前内存剩余量: " + memory.get(ITDragonCoreParam.FREE.getValue()));
			System.out.println("--------------------------------------------");

			ctx.writeAndFlush("info received!");
		} else {
			ctx.writeAndFlush("connect failure!").addListener(ChannelFutureListener.CLOSE);
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
