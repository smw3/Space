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
    private Button btn;
    private EditField field;

    @Override
    protected RootPane createRootPane() {
        RootPane rp = super.createRootPane();
        rp.setTheme("loginMenu");

        btn = new Button("Loginnnnnnnnnnnnnnn");
        btn.addCallback(new Runnable() {
            public void run() {
                System.out.println("It works!");
            }
        });
        field = new EditField();

        rp.add(btn);
        rp.add(field);
        return rp;
    }

    @Override
    protected void layoutRootPane() {
        btn.adjustSize();
        btn.setPosition(200, 200);
        field.setPosition(100, 100);
        field.setSize(160, 16);
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
