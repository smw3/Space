package de.schaf.space.net;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryo.Kryo;

import de.schaf.space.net.packet.*;

public class Network {
	public static final int TCP_Port = 54554;
	public static final int UDP_Port = 54555;
	public static final String Server_Adress = "localhost";
	
	static public void register(EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		
		kryo.register(SpacePacket.class);
		kryo.register(PacketClientLogin.class);
		kryo.register(PacketServerLoginSuccessfull.class);
		kryo.register(PacketServerLoginFailed.class);
	}
}
