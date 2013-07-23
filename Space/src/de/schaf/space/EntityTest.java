package de.schaf.space;

public class EntityTest extends Entity {

	public EntityTest() {
		this.setPosition(400, 300);
		this.setSize(100f,100f);
		
		this.setAngle(.7f);
	}

	@Override
	public void update(int delta) {
		this.setAngle(this.getAngle() + 0.001f*delta);		
	}

}
