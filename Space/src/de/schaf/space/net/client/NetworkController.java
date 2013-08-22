package de.schaf.space.net.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;

import de.schaf.space.User;

public class NetworkController {
	
	private class connectionThread extends Thread {
	
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
						parseConnectionData(input);
					}
					
				} catch (IOException e) {
					e.printStackTrace();
					connectionFailed();
					return;
				}

			}
		}
		
		private Socket serverSocket;
		
		private String serverAdress;
		private Integer serverPort;
		
		public connectionThread() {

		}
		
		public void connect(String serverAdress, Integer serverPort) {
			this.serverAdress = serverAdress;
			this.serverPort = serverPort;
			this.start();
		}
		
		public void run() {
			try {
				serverSocket = new Socket(serverAdress,serverPort);
			} catch (IOException e) {
				e.printStackTrace();
				connectionFailed();
				return;
			}
			
			(new inboundThread(serverSocket)).start();
			
			PrintStream os;
			try {
				os = new PrintStream( serverSocket.getOutputStream() );
			} catch (IOException e) {
				e.printStackTrace();
				connectionFailed();
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
	}
	
	private static NetworkController instance;
	
	private NetworkErrorListener networkErrorListener = null;
	
	private connectionThread connectionThread = new connectionThread();
	
	private volatile Queue<String> outboundQueue = new LinkedList<String>();
	
	private final String serverAdress = "localhost";
	private final Integer serverPort = 3567;
	
	private User user;
	
	public NetworkController() {
		
	}
	
	public void createUser(String username, String password) {
		user = new User(username,password);
	}

	public static NetworkController getInstance() {
		if (instance == null) {
			System.out.println("Created new NetworkController");
			instance = new NetworkController();
		}
		return instance;
	}
	
	private connectionThread getNetworkThread() {
		return connectionThread;
	}
	
	public void connect() {
		getNetworkThread().connect(serverAdress,serverPort);
		sendMessage("Hello world!");
	}
	
	public void sendMessage(String P) {
		outboundQueue.add(P);
	}


	public void connectionFailed() {
		connectionThread = new connectionThread(); // Most likely a bad idea 
		if (networkErrorListener != null) networkErrorListener.connectionError("Failed to connect!");
	}
	
	public synchronized void parseConnectionData(String data) {
		System.out.println("Data: "+data);
	}

	public void setErrorListener(NetworkErrorListener nel) {
		networkErrorListener = nel;
	}
}
