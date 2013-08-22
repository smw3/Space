package de.schaf.space.net;

public interface ConnectionThreadListener {
	public void parseConnectionData(String data);
	public void connectionFailed();
}
