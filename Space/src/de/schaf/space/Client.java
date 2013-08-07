package de.schaf.space;

import org.newdawn.slick.AppGameContainer;

import de.matthiasmann.twl.GUI;
import de.matthiasmann.twl.renderer.lwjgl.LWJGLRenderer;

public class Client {
	
	public static void main(String[] args) throws Exception {
	
		AppGameContainer app = new AppGameContainer(new StateController("foobar"));
		
		app.setDisplayMode(800, 600, false);
		app.start();
	}

}
