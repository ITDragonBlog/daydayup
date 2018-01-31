package com.itdragon.monitoring;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;  
  
  
public class ITDragonClientHandler extends ChannelInboundHandlerAdapter{  
      
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);  
      
    private ScheduledFuture<?> heartBeat;  
    //主动向服务器发送认证信息  
    private InetAddress addr ;  
      
    @Override  
    public void channelActive(ChannelHandlerContext ctx) throws Exception {  
        System.out.println("Client 连接一开通就开始验证.....");  
        addr = InetAddress.getLocalHost();  
        String ip = addr.getHostAddress();  
        System.out.println("ip : " + ip);
        String key = ITDragonCoreParam.SALT_KEY.getValue(); // 假装进行了很复杂的加盐加密  
        // 按照Server端的格式，传递令牌   
        String auth = ip + "," + key;   
        ctx.writeAndFlush(auth);  
    }  
      
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
        try {  
            if(msg instanceof String){  
                String result = (String) msg;  
                if(ITDragonCoreParam.AUTH_SUCCESS.getValue().equals(result)){  
                    // 验证成功，每隔10秒，主动发送心跳消息  
                    this.heartBeat = this.scheduler.scheduleWithFixedDelay(new HeartBeatTask(ctx), 0, 10, TimeUnit.SECONDS);  
                    System.out.println(msg);                  
                }  
                else {  
                    System.out.println(msg);  
                }  
            }  
        } finally {  
            ReferenceCountUtil.release(msg);  
        }  
    }  
      
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
        cause.printStackTrace();  
        if (heartBeat != null) {  
            heartBeat.cancel(true);  
            heartBeat = null;  
        }  
        ctx.fireExceptionCaught(cause);  
    }  
  
  
}  
class HeartBeatTask implements Runnable {  
	
	private final ChannelHandlerContext ctx;  
	
	public HeartBeatTask(final ChannelHandlerContext ctx) {  
		this.ctx = ctx;  
	}  
	
	public void run() {  
		try {  
			// 采用sigar 获取本机数据，放入实体类中  
			ITDragonRequestInfo info = new ITDragonRequestInfo();  
			info.setIp(InetAddress.getLocalHost().getHostAddress()); // ip  
			Sigar sigar = new Sigar();  
			
			CpuPerc cpuPerc = sigar.getCpuPerc();  
			HashMap<String, Object> cpuPercMap = new HashMap<String, Object>();  
			cpuPercMap.put(ITDragonCoreParam.COMBINED.getValue(), cpuPerc.getCombined());  
			cpuPercMap.put(ITDragonCoreParam.USER.getValue(), cpuPerc.getUser());  
			cpuPercMap.put(ITDragonCoreParam.SYS.getValue(), cpuPerc.getSys());  
			cpuPercMap.put(ITDragonCoreParam.WAIT.getValue(), cpuPerc.getWait());  
			cpuPercMap.put(ITDragonCoreParam.IDLE.getValue(), cpuPerc.getIdle());  
			
			Mem mem = sigar.getMem();  
			HashMap<String, Object> memoryMap = new HashMap<String, Object>();  
			memoryMap.put(ITDragonCoreParam.TOTAL.getValue(), mem.getTotal() / 1024L);  
			memoryMap.put(ITDragonCoreParam.USED.getValue(), mem.getUsed() / 1024L);  
			memoryMap.put(ITDragonCoreParam.FREE.getValue(), mem.getFree() / 1024L);  
			info.setCpuPercMap(cpuPercMap);  
			info.setMemoryMap(memoryMap);  
			ctx.writeAndFlush(info);  
			
		} catch (Exception e) {  
			e.printStackTrace();  
		}  
	}  
	
}  
