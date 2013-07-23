package de.schaf.space;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public class RendererTriangle implements Renderer {

	public RendererTriangle() {
		
	}

	@Override
	public void render(Graphics G, Entity Target) {
		Vector2f Pos = Target.getPosition();
		
		float width = Target.getWidth();
		float height = Target.getHeight();
		
		int numRects = (int)(height/15);
		Rectangle[] Rectangles = new Rectangle[numRects];
		
		Transform rotation = Transform.createRotateTransform(Target.getAngle(),Target.getPosition().x,Target.getPosition().y);
		
		for (int i=0;i<numRects;i++) {
			Rectangles[i] = new Rectangle(0,0,width * ((float)i / (float)numRects),15);
			Rectangles[i].setCenterX(Pos.x);
			Rectangles[i].setCenterY(Pos.y + i*15 - height/2 + 7);
			
			G.draw(Rectangles[i].transform(rotation));
			//G.draw(Rectangles[i]);
		}
		
		int stringLen = G.getFont().getWidth(Target.getName());
		int EntityHeight = (int) Math.max(Target.getHeight(), Target.getWidth());
		G.drawString(Target.getName(), Pos.x-stringLen/2, Pos.y-EntityHeight);

	}

}
