package de.schaf.space;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.geom.Vector2f;

public class EntityControlledPlayer extends EntityMoving {

	public EntityControlledPlayer() {
		this.setPosition(400, 300);
		this.setSize(30f,60f);
	}
	
	@Override
	public void update(int delta) {
		super.update(delta);
		Vector2f Pos = simFlight(10);
		
		if (Pos.x < 0 || Pos.x > 800) bounce();
		if (Pos.y < 0 || Pos.y > 600) bounce();
	}
	
	public void bounce() {
		boolean xbounce = false;
		boolean ybounce = false;
		
		Vector2f Pos = getPosition();
		
		if (Pos.x < 0 || Pos.x > 800) xbounce = true;
		if (Pos.y < 0 || Pos.y > 600) ybounce = true;
		
		System.out.println(xbounce+" "+ybounce);
		
		Vector2f vel = getVelocity();
		Vector2f newvel = new Vector2f();
		
		if (!xbounce) newvel.x = vel.x;
		else newvel.x = -vel.x;
		
		if (!ybounce) newvel.y = vel.y;
		else newvel.y = -vel.y;
		
		setVelocity(newvel);
		
	}
	
	@KeyInput (Keyboard.KEY_W)
	public void Accelerate(int delta) {
		Vector2f Acc = new Vector2f(0,0.0001f);
		Acc.setTheta(Math.toDegrees(getAngle() - Math.PI/2));
		this.setAcceleration(Acc);
	}
	
	@KeyInput (Keyboard.KEY_S)
	public void Deccalerate(int delta) {
		Vector2f Acc = new Vector2f(0,0.0001f);
		Acc.setTheta(Math.toDegrees(getAngle() - Math.PI/2));
		Acc.negateLocal();
		this.setAcceleration(Acc);
	}
	
	public void turn(float amnt) {
		this.setAngle(this.getAngle()+amnt);
	}
	
	@KeyInput(Keyboard.KEY_A)
	public void turnLeft(int delta) {
		turn(-.001f*delta);
	}
	
	@KeyInput(Keyboard.KEY_D)
	public void turnRight(int delta) {
		turn(.001f*delta);
	}	
	
	@KeyInput (value = Keyboard.KEY_F, state = KeyInput.State.PRESS)
	public void test() {
		System.out.println("f");
	}

}
