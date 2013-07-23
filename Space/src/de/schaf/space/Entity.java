package de.schaf.space;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Entity {
	// Purely a way to save position and size, not representative of hitbox or graphic
	private Rectangle positionSize = new Rectangle(0f,0f,0f,0f);
	private float angle = 0f;
	
	private String name = "";
	
	public Entity() {

	}

	public Vector2f getPosition() {
		return new Vector2f(positionSize.getX(),positionSize.getY());
	}

	public void setPosition(Vector2f position) {
		positionSize.setX(position.x);
		positionSize.setY(position.y);
	}
	
	public void setPosition(float x, float y) {
		positionSize.setX(x);
		positionSize.setY(y);
	}
	
	public float getWidth() {
		return positionSize.getWidth();
	}
	
	public float getHeight() {
		return positionSize.getHeight();
	}
	
	public void setSize(Vector2f size) {
		positionSize.setSize(size.x, size.y);
	}
	
	public void setSize(float w, float h) {
		positionSize.setSize(w,h);
	}
	
	public void setWidth(float w) {
		positionSize.setWidth(w);
	}
	
	public void setHeight(float w) {
		positionSize.setHeight(w);
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
		while (this.angle > 2*Math.PI) this.angle -= 2*Math.PI;
	}
	
	public void update(int delta) {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
