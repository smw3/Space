package de.schaf.space.net.server;

import java.io.IOException;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Listener.ThreadedListener;
import com.esotericsoftware.kryonet.Server;

import de.schaf.space.net.packet.PacketClientLogin;
import de.schaf.space.net.packet.PacketServerLoginSuccessfull;
import de.schaf.space.net.Network;

public class SpaceServer {
	

	public static void main(String[] args) throws IOException {
		(new SpaceServer()).start();
	}

	private void start() throws IOException {
		Server server = new Server();
		
		Network.register(server);
		
		server.start();
		server.bind(Network.TCP_Port, Network.UDP_Port);
		
		server.addListener(new ThreadedListener(new Listener() {
			   public void received (Connection connection, Object object) {
			      if (object instanceof PacketClientLogin) {
			    	 PacketClientLogin request = (PacketClientLogin)object;
			         System.out.println(request.Username+" wants to login with password "+request.Password);

			         PacketServerLoginSuccessfull response = new PacketServerLoginSuccessfull();
			         connection.sendTCP(response);
			      }
			   }
			}));
	}
}
