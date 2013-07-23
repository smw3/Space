package de.schaf.space;

import org.newdawn.slick.geom.Vector2f;

public class EntityMoving extends Entity {

	private Vector2f Velocity = new Vector2f();

	private Vector2f Acceleration = new Vector2f();
	
	public EntityMoving() {
		
	}
	
	public Vector2f getVelocity() {
		return new Vector2f(Velocity);
	}

	public void setVelocity(Vector2f velocity) {
		Velocity.set(velocity);
	}
	
	public void addVelocity(Vector2f addVel) {
		Velocity.add(addVel);
	}

	public Vector2f getAcceleration() {
		return new Vector2f(Acceleration);
	}

	public void setAcceleration(Vector2f acceleration) {
		Acceleration.set(acceleration);
	}
	
	public Vector2f simFlight(int delta) {
		Vector2f Pos = getPosition();
		Vector2f Vel = getVelocity();
		Vector2f Acc = getAcceleration();
		
		Acc.scale(.5f*delta*delta);
		Vel.scale(delta);
		
		Vector2f newPos = Pos.add(Vel.add(Acc));
		
		return newPos;
	}
	
	@Override
	public void update(int delta) {
		Vector2f VelocityAfterAcceleration = getVelocity().add(Acceleration);
		Vector2f PositionAfterVelocity = getPosition().add(Velocity);
		
		setAcceleration(new Vector2f());		
		setVelocity(VelocityAfterAcceleration);
		setPosition(PositionAfterVelocity);	
	}
	

}
