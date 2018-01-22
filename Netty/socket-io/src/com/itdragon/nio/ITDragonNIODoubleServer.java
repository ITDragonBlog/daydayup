package com.itdragon.nio;

public class ITDragonNIODoubleServer {
	
	private static Integer DEFAULT_PORT = 8888;
	private static ITDragonNIODoubleServerHandler serverHandle;

	public static void start() {
		start(DEFAULT_PORT);
	}

	public static synchronized void start(Integer port) {
		if (serverHandle != null) {
			serverHandle.stop();
		}
		serverHandle = new ITDragonNIODoubleServerHandler(port);
		new Thread(serverHandle, "Server").start();
	}
}
