package de.schaf.space.net.client;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

import de.schaf.space.net.Network;
import de.schaf.space.net.packet.PacketClientLogin;
import de.schaf.space.net.packet.SpacePacket;

public class ClientNetworkController implements Runnable {

	private Client client;
	private Thread thread;

	private boolean running = false;
	
	private volatile Queue<SpacePacket> TCP_OutgoingQueue = new LinkedList<SpacePacket>();
	private volatile Queue<SpacePacket> UDP_OutgoingQueue = new LinkedList<SpacePacket>();
	
	private Listener ClientListener;

	public ClientNetworkController() {
		client = new Client();
		client.start();

		Network.register(client);
	}

	public synchronized void start() {
		running = true;

		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		running = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isRunning() {
		return running;
	}

	public void sendPacket(SpacePacket Packet) {
		sendPacket(Packet,true);
	}
	
	public void sendPacket(SpacePacket Packet, boolean UDP) {
		if (!isRunning()) start();
		if (!UDP) {
			TCP_OutgoingQueue.add(Packet);
		} else {
			UDP_OutgoingQueue.add(Packet);
		}
	}

	public void login(String username, String password) {
		sendPacket(new PacketClientLogin(username, password),true);
	}

	@Override
	public void run() {
		try {
			while (running) {
				if (!client.isConnected()) {
					client.connect(2000, Network.Server_Adress,
							Network.TCP_Port, Network.UDP_Port);
				}
				
				SpacePacket P;
				while ((P = TCP_OutgoingQueue.poll()) != null) {
					client.sendTCP(P);
				}
				while ((P = UDP_OutgoingQueue.poll()) != null) {
					client.sendUDP(P);
				}
				
			}
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setListener(Listener listener) {
		if (ClientListener != null) client.removeListener(ClientListener);
		client.addListener(listener);
		
		ClientListener = listener;		
	}

}
