package de.schaf.space;

import org.newdawn.slick.AppGameContainer;

import de.matthiasmann.twl.GUI;
import de.matthiasmann.twl.renderer.lwjgl.LWJGLRenderer;
import de.schaf.space.net.client.ClientNetworkController;

public class Game {
	
	public static StateController StateController = new StateController("foobar");
	public static ClientNetworkController ClientNetworkController = new ClientNetworkController();
	
	public static void main(String[] args) throws Exception {
	
		AppGameContainer app = new AppGameContainer(StateController);
		
		app.setDisplayMode(800, 600, false);
		app.start();
	}

}
