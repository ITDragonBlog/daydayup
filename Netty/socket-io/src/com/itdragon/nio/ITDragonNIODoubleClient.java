package com.itdragon.nio;

public class ITDragonNIODoubleClient {
	
	private static String DEFAULT_HOST = "127.0.0.1";
	private static Integer DEFAULT_PORT = 8888;
	private static ITDragonNIODoubleClientHandler clientHandle;

	public static void start() {
		start(DEFAULT_HOST, DEFAULT_PORT);
	}

	public static synchronized void start(String ip, int port) {
		if (clientHandle != null) {
			clientHandle.stop();
		}
		clientHandle = new ITDragonNIODoubleClientHandler(ip, port);
		new Thread(clientHandle, "Server").start();
	}

	// 向服务器发送消息
	public static boolean sendMsg(String msg) throws Exception {
		if (msg.equals("q"))
			return false;
		clientHandle.sendMsg(msg);
		return true;
	}
}
