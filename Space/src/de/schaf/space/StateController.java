package de.schaf.space;

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
		addState(new StateGame());		
	}


	@Override
	protected URL getThemeURL() {
		return null;
	}
}
