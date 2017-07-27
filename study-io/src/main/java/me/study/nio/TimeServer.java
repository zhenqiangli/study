package me.study.nio;

public class TimeServer {
	
	private static final int PORT = 8080;
	
	public static void main(String[] args) {
		MultiplexerTimeServer timeServer = new MultiplexerTimeServer(PORT);
		new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
	}

}
