package de.schaf.space;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.EditField;

import TWLSlick.BasicTWLGameState;
import TWLSlick.RootPane;

public class StateLogin extends BasicTWLGameState {

	private LoginFrame loginFrame;
	
    @Override
    protected RootPane createRootPane() {
        RootPane rp = super.createRootPane();

        loginFrame = new LoginFrame();
       
        rp.add(loginFrame);
        return rp;
    }

    @Override
    protected void layoutRootPane() {
        //loginFrame.adjustSize();
        loginFrame.setPosition(100, 100);
    }

	@Override
	public int getID() {
		return 0;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

}
