package com.itdragon.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ITDragonNIODoubleClientHandler implements Runnable {

	private String host;
	private Integer port;
	private Selector selector;
	private SocketChannel socketChannel;
	private volatile boolean started;

	public ITDragonNIODoubleClientHandler(String ip, Integer port) {
		this.host = ip;
		this.port = port;
		try {
			selector = Selector.open();
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			started = true;
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void stop() {
		started = false;
	}

	@Override
	public void run() {
		doConnect();
		while (started) {
			try {
				selector.select();
				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
				SelectionKey key = null;
				while (iterator.hasNext()) {
					key = iterator.next();
					iterator.remove();
					handleInput(key);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (null != selector) {
			try {
				selector.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void handleInput(SelectionKey key) throws IOException {
		if (key.isValid()) {
			SocketChannel socketChannel = (SocketChannel) key.channel();
			if (key.isConnectable()) {
				if (!socketChannel.finishConnect()) {
					System.exit(1);
				}
			}
			if (key.isReadable()) {
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				int readBytes = socketChannel.read(buffer);
				if (0 < readBytes) {
					buffer.flip();
					byte[] bytes = new byte[buffer.remaining()];
					buffer.get(bytes);
					String result = new String(bytes, "UTF-8");
					System.out.println("客户端收到消息：" + result);
				} else if (0 > readBytes) {
					key.cancel();
					socketChannel.close();
				}
			}
		}
	}

	private void doWrite(SocketChannel channel, String request) throws IOException {
		byte[] bytes = request.getBytes();
		ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
		writeBuffer.put(bytes);
		writeBuffer.flip();
		channel.write(writeBuffer);
	}

	private void doConnect() {
		try {
			if (!socketChannel.connect(new InetSocketAddress(host, port))) {
				socketChannel.register(selector, SelectionKey.OP_CONNECT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMsg(String msg) throws Exception {
		socketChannel.register(selector, SelectionKey.OP_READ);
		doWrite(socketChannel, msg);
	}

}
