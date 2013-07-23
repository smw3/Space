package de.schaf.space;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.*;

public class RendererRectangle implements Renderer {
	
	public RendererRectangle() {

	}

	@Override
	public void render(Graphics G, Entity Target) {
		Vector2f Pos = Target.getPosition();
		
		float width = Target.getWidth();
		float height = Target.getHeight();
		Rectangle R = new Rectangle(0,0,width,height);
		R.setCenterX(Pos.x);
		R.setCenterY(Pos.y);
		
		Transform rotation = Transform.createRotateTransform(Target.getAngle(),R.getCenterX(),R.getCenterY());
		
		G.draw(R.transform(rotation));
		
		
		int stringLen = G.getFont().getWidth(Target.getName());
		int EntityHeight = (int) Math.max(Target.getHeight(), Target.getWidth());
		G.drawString(Target.getName(), Pos.x-stringLen/2, Pos.y-EntityHeight);
	}
}
