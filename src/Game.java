public class Game {

    private Window window;

    private int ups;    // updates per second
    private int fps;    // frames per second

    private boolean RENDER_TIME = false; // display ups and fps to standard output

    public Game() {
        window = new Window();

        ups = 60;
        fps = 60;

        init();
    }

    private void init() {
        
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
		window.update();
	}
	
	private void render() {
		window.render(new Object[0]); // FIXME
	}

    public static void main(String[] args) {
        new Game().run();
    }

}