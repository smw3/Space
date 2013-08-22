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
import de.schaf.space.net.ConnectionThread;
import de.schaf.space.net.ConnectionThreadListener;

public class NetworkController implements ConnectionThreadListener {
	
	private static NetworkController instance;
	
	private NetworkErrorListener networkErrorListener = null;
	
	private ConnectionThread connectionThread = new ConnectionThread(this);
	
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
	
	private ConnectionThread getNetworkThread() {
		return connectionThread;
	}
	
	public void connect() {
		if (!getNetworkThread().isConnected()) {
			getNetworkThread().connect(serverAdress,serverPort);
		}
		getNetworkThread().sendMessage("Hello world!");
	}

	public void connectionFailed() {
		connectionThread = new ConnectionThread(this); // Most likely a bad idea 
		throwError("Failed to connect!");
	}
	
	private void throwError(String err) {
		if (networkErrorListener != null) networkErrorListener.connectionError(err);		
	}
	
	public synchronized void parseConnectionData(String data) {
		System.out.println("Data: "+data);
	}

	public void setErrorListener(NetworkErrorListener nel) {
		networkErrorListener = nel;
	}
}
