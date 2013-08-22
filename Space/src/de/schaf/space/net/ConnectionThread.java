package de.schaf.space.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectionThread extends Thread {

	private String serverAdress;
	private Integer serverPort;
	private Socket serverSocket;
	
	private ConnectionThreadListener listener = null;
	
	private volatile Queue<String> outboundQueue = new LinkedList<String>();
	
	private class inboundThread extends Thread {
		private Socket serverSocket;
		
		public inboundThread(Socket serverSocket) {
			this.serverSocket = serverSocket;
		}
		
		public void run() {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader( serverSocket.getInputStream()));

				while(true) {
					String input = in.readLine();
					listener.parseConnectionData(input);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
				listener.connectionFailed();
				return;
			}

		}
	}

	public ConnectionThread(ConnectionThreadListener listener) {
		this.listener = listener;
	}
	
	public void connect(String serverAdress, Integer serverPort) {
		this.serverAdress = serverAdress;
		this.serverPort = serverPort;
		this.start();
	}
	
	public void sendMessage(String P) {
		outboundQueue.add(P);
	}
	
	public void run() {
		try {
			this.serverSocket = new Socket(this.serverAdress,this.serverPort);
		} catch (IOException e) {
			e.printStackTrace();
			listener.connectionFailed();
			return;
		}
		
		(new inboundThread(serverSocket)).start();
		
		PrintStream os;
		try {
			os = new PrintStream(serverSocket.getOutputStream() );
		} catch (IOException e) {
			e.printStackTrace();
			listener.connectionFailed();
			return;
		}
		
		while (true) {
			while (!outboundQueue.isEmpty()) {
				String dataToSend = outboundQueue.poll();
				
				System.out.print("Send: ");
				System.out.print(dataToSend +" ");
				System.out.println();
				
				os.println(dataToSend);
			}
		}
	}

	public boolean isConnected() {
		if (serverSocket == null) return false;
		return serverSocket.isConnected();
	}
}

