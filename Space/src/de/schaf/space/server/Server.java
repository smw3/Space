package de.schaf.space.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


	public static void main(String[] args) throws IOException {
		(new Server()).start();
	}

	private void start() throws IOException {
		ServerSocket server = new ServerSocket( 3567 );
		// Timeout nach 10 Minuten
		server.setSoTimeout( 600000 );
		try {
		  Socket client = server.accept();
			
		  InputStream  in  = client.getInputStream();
		  OutputStream out = client.getOutputStream();
		  
		  BufferedReader inReader = new BufferedReader(new InputStreamReader(in));
		  PrintStream outStream = new PrintStream(out);
		  while (true) {
			  String inData = inReader.readLine();
			  
			  outStream.println("I received: "+inData);
		  }
		  
		  
		} catch ( InterruptedIOException e ) {
		  System.err.println( "Timeout after ten minutes" );
		}

		
	}

}
