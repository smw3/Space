package de.schaf.space;

import org.newdawn.slick.AppGameContainer;

public class Client {

	/**
	 * @param args
	 */

	/* Main */

	public static void main(String[] args) throws Exception {
		AppGameContainer app = new AppGameContainer(new StateController("foobar"));

		app.setDisplayMode(800, 600, false);
		app.start();
	}

}
