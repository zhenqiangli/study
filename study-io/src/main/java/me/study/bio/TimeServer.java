package me.study.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TimeServer {

	private static final int PORT = 8080;

	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("The timeserver is start in port: " + PORT);

			Socket socket = null;
			while (true) {
				socket = serverSocket.accept();
				new Thread(new TimeServerHandler(socket)).start();
			}
		} finally {
			if (serverSocket != null) {
				System.out.println("The timeserver close");
				serverSocket.close();
				serverSocket = null;
			}
		}

	}

}

class TimeServerHandler implements Runnable {

	private Socket socket;

	public TimeServerHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		BufferedReader in = null;
		PrintWriter out = null;

		try {
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			out = new PrintWriter(this.socket.getOutputStream(), true);

			String currentTime = null;
			String body = null;

			while (true) {
				body = in.readLine();
				if (body == null) {
					break;
				}

				System.out.println("The timeserver receive order : " + body);
				currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)
						? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
				out.println(currentTime);
			}
		} catch (Exception e) {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (out != null) {
				out.close();
				out = null;
			}

			if (this.socket != null) {
				try {
					this.socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				this.socket = null;
			}
		}
	}

}
