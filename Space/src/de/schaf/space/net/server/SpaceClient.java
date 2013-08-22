package de.schaf.space.net.server;

import java.net.Socket;

import de.schaf.space.User;

public class SpaceClient {
	private Socket socket;
	
	private User user;

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
