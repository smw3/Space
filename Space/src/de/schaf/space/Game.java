package de.schaf.space;

import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glOrtho;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

public class Game {

	public static final int RENDER_WIDTH = 1024;
	public static final int RENDER_HEIGHT = 768;
	
	private boolean vsync = false;
	
	private long lastFrame;
    private long lastFPS;
    private int fps;
    long delta;

	
    public void start() throws LWJGLException {
        init();
        initGL();
        //initTextures();
        lastFPS = getTime(); //set lastFPS to current Time
        while (!Display.isCloseRequested()) {
            getDelta();
            logic(delta);
            cls();
            if (Display.isActive() || Display.isVisible() || Display.isDirty()) {
                render();
            }
            Display.update();
            // run at 60 FPS
            if (vsync) Display.sync(60);
            updateFPS();
        }
        cleanup();
    }
    
    public void init() throws LWJGLException {
        Display.setTitle("Space!");
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        Mouse.create();
     }
	
    public void updateFPS() {
        if (getTime() - lastFPS > 1000) {
            Display.setTitle("FPS: " + fps);
            fps = 0; //reset the FPS counter
            lastFPS += 1000; //add one second
        }
        fps++;
    }

    public long getTime() {
        return System.nanoTime() / 1000000;
    }

    public void getDelta() {
        long time = getTime();
        delta = (int) (time - lastFrame);
        lastFrame = time;
    }
    
    private void logic(long delta) {
        //float delta_mod = (float) delta / 1000;

    }

    
    /* Graphics stuff */


	private void initGL() {
        if (!GLContext.getCapabilities().GL_EXT_framebuffer_object) {
            System.err.println("FBO not supported");
        }
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();

        glOrtho(0, RENDER_WIDTH, RENDER_HEIGHT, 0, 10, -10);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();

        GL11.glDisable(GL11.GL_DEPTH_TEST);

        GL11.glEnable(GL11.GL_TEXTURE_2D);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }
    
    private void render() {
        GL11.glPushMatrix();

        //GL11.glTranslatef(Camera.GetX(), Camera.GetY(), 0f);
        //GL11.glScalef(Camera.GetZoom(), Camera.GetZoom(), 1f);

        drawObjects();

        GL11.glPopMatrix();
    }
    
    private void drawObjects() {
    	//
        
    }
    
    private void cleanup() {
        Display.destroy();
    }

    private void cls() {
        // clear the screen
        glClearColor(0.0f, 1.0f, 0.0f, 0.5f);
        //glClear(GL_COLOR_BUFFER_BIT | GL_STENCIL_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }
    
	
	public static void main(String[] args) throws Exception {
		(new Game()).start();
	}

}
