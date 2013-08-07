package de.schaf.space;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import TWLSlick.TWLStateBasedGame;

public class StateController extends TWLStateBasedGame {

	public StateController(String name) {
		super(name);
	}
	

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new StateLogin());	
		addState(new StateGame());		
	}


	@Override
	protected URL getThemeURL() {

		try {
			return (new File("./ui/theme.xml")).toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
		
		//return Client.class.getResource("./ui/theme.xml");
	}
}
