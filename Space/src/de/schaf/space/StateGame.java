package de.schaf.space;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StateGame extends BasicGameState {

	private boolean vsync = false;

	private long lastFrame;
	private long lastFPS;
	private int fps;
	long delta;

	private boolean showHitbox = false;
	private Renderer hitboxRenderer = new RendererRectangle();

	public HashMap<Entity, Renderer> GameObjects = new HashMap<Entity, Renderer>();

	private Input input;

	public StateGame() {
		super();
	}

	/* Gameloop */

	@Override
	public void init(GameContainer gc, StateBasedGame stateController) throws SlickException {
		Entity E = new EntityControlledPlayer();
		Renderer R = new RendererTriangle();
		GameObjects.put(E, R);

		input = new Input(gc.getHeight());
	}

	@Override
	public void render(GameContainer gc, StateBasedGame stateController, Graphics g) throws SlickException {
		for (Entry<Entity, Renderer> GameObject : GameObjects.entrySet()) {
			Renderer R;

			if (!showHitbox) {
				R = GameObject.getValue();
			} else {
				R = hitboxRenderer;
			}

			Entity E = GameObject.getKey();

			R.render(g, E);
		}

	}

	@Override
	public void update(GameContainer arg0, StateBasedGame stateController, int delta) throws SlickException {
		handleInput(delta);

		for (Entry<Entity, Renderer> GameObject : GameObjects.entrySet()) {
			Entity E = GameObject.getKey();
			E.update(delta);
		}
	}

	/* Input */
	//Event based for one-time presses
	@Override
	public void keyPressed(int pressedKey, char c) {
		if (pressedKey == Input.KEY_F3) {
			showHitbox = !showHitbox;
		}
		
		for (Entry<Entity, Renderer> GameObject : GameObjects.entrySet()) {
			Entity E = GameObject.getKey();

			for (Method method : E.getClass().getMethods()) {
				for (Annotation a : method.getAnnotations()) {
					if (a.annotationType().equals(KeyInput.class)) {
						int Key = ((KeyInput) a).value();
						if (((KeyInput) a).state() != KeyInput.State.PRESS) break;
						if (pressedKey == Key) {
							try {
								//System.out.println("Triggering via event");
								method.invoke(E);
							} catch (IllegalAccessException
									| IllegalArgumentException
									| InvocationTargetException e) {
								System.err.println("Class "
												+ E.getClass().toString()
												+ " fails to implement the KeyInput method "
												+ method.toString()
												+ " properly");
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	
	//Poll based for continuous presses
	public void handleInput(int delta) {
		for (Entry<Entity, Renderer> GameObject : GameObjects.entrySet()) {
			Entity E = GameObject.getKey();

			for (Method method : E.getClass().getMethods()) {
				for (Annotation a : method.getAnnotations()) {
					if (a.annotationType().equals(KeyInput.class)) {
						int Key = ((KeyInput) a).value();
						if (((KeyInput) a).state() != KeyInput.State.DOWN) break;
						
						if (input.isKeyDown(Key)) {
							try {
								//System.out.println("Triggering via poll");
								method.invoke(E, delta);
							} catch (IllegalAccessException
									| IllegalArgumentException
									| InvocationTargetException e) {
								System.err.println("Class "
												+ E.getClass().toString()
												+ " fails to implement the KeyInput method "
												+ method.toString()
												+ " properly");
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

	/* Helper functions */

	public long getTime() {
		return System.nanoTime() / 1000000;
	}

	public void getDelta() {
		long time = getTime();
		delta = (int) (time - lastFrame);
		lastFrame = time;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
