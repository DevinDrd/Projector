import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import java.io.IOException;

import Math.*;

public class Window {

    private int width = 600;
    private int height = 600;

    private String title = "Game";

    private float[] clearColor = new float[] {.01f, .01f, .1f, 1.0f};

    private Shader vertexShader;
    private Shader fragmentShader;

    private String projUni = "MVP";
    private Matrix projection;

    private OpenGL openGL;

    private static boolean ortho = true; // TODO: REMOVEME

    public Window() {
        openGL = new OpenGL(width, height, title, clearColor);
        
        projection = ortho ? Matrix.ortho(-10, 10, -10, 10, -10, 10): Matrix.frustum(-10, 10, -10, 10, 1, 10);
        ortho = false; // TODO: REMOVEME

        try {
			vertexShader = new Shader("./res/shaders/VertexShader.txt");
			fragmentShader = new Shader("./res/shaders/FragmentShader.txt");
		} catch (IOException e) {
            System.out.println("There was an error reading a shader.");
            System.exit(1);
        }
        
        openGL.setShader(vertexShader);
        openGL.setShader(fragmentShader);

    }

    public void update() {
        openGL.update();
    }

    public void render(Map map) {
        float[] vertices = map.getVertices();
        float[] colors = map.getColors();

        openGL.setUniMat4(projUni, projection);
        openGL.render(vertices, colors);
    }

    public ArrayList<KeyEvent> getEvents() {
        return openGL.pollEvents();
    }

    public boolean isOpen() {
        return openGL.isOpen();
    }

    public void close() {
        openGL.close();
    }
    
}
