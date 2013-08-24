package de.schaf.space.net.packet;

public class PacketClientLogin implements SpacePacket {
	public String Username;
	public String Password;
	
	public PacketClientLogin() {
		
	}
	
	public PacketClientLogin(String username, String password) {
		this.Username = username;
		this.Password = password;
	}
}
