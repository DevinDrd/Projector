package Game;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import java.io.FileNotFoundException;
import java.io.IOException;

import Game.Graphics.Window;
import Game.Input.*;
import Game.Physics.PhysicsEngine;

public class Game {

	private Window window;
	
	private Controller controller;

	private PhysicsEngine physicsEngine;

    private int ups;    // updates per second
    private int fps;    // frames per second

	private boolean RENDER_TIME = false; // display ups and fps to standard output

	private Level level;
	private String mapPath;

    public Game() {
		mapPath = "./res/maps/map.txt";

		init();
	}
	
	public Game(String path) {		
		mapPath = path;

		init();
    }

    private void init() {
		window = new Window();

		physicsEngine = new PhysicsEngine();

        ups = 60;
		fps = 60;

		try {
			level = new Level(mapPath);
		} catch (FileNotFoundException e) {
			System.out.println("Could not file: " + mapPath);
			System.exit(0);
		} catch(IOException e) {
			System.out.println("Could not load texture");
		} catch (InputMismatchException e) {
			System.out.println("Unable to pars file: " + mapPath);
			System.exit(0);
		} catch (NoSuchElementException e) {
			System.out.println("Unable to pars file: " + mapPath);
			System.exit(0);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("Illegal path: " + mapPath);
			System.exit(0);
		}

		window.addTextures(level);
		controller = new Controller(level.getPlayers().get(0));
    }

    private void run() {
		long initialTime = System.nanoTime();
		final double timeU = 1000000000 / ups;
		final double timeF = 1000000000 / fps;
		double deltaU = 0, deltaF = 0;
		int frames = 0, ticks = 0;
		long timer = System.currentTimeMillis();
		
		while (window.isOpen()) {
			
	        long currentTime = System.nanoTime();
	        deltaU += (currentTime - initialTime) / timeU;
	        deltaF += (currentTime - initialTime) / timeF;
	        initialTime = currentTime;

	        if (deltaU >= 1) {
	            update();
	            ticks++;
	            deltaU--;
	        }

	        if (deltaF >= 1) {
	            render();
	            frames++;
	            deltaF--;
	        }

	        if (System.currentTimeMillis() - timer > 1000) {
	            if (RENDER_TIME)
	                System.out.println(String.format("UPS: %s, FPS: %s", ticks, frames));
	            frames = 0;
	            ticks = 0;
	            timer += 1000;
	        }
		}
		
		window.close();
	}
	
	private void update() {
		window.update(); // updates window events
		physicsEngine.update(level.getHardEntities());
		controller.update(window.getEvents());
		level.update();
	}
	
	private void render() {
		window.render(level);
	}

    public static void main(String[] args) {
		new Game("./res/maps/map.txt").run();
    }

}