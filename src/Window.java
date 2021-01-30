import java.io.IOException;

public class Window {

    private int width = 600;
    private int height = 600;

    private String title = "Game";

    private float[] clearColor = new float[] {.01f, .01f, .1f, 1.0f};

    private Shader vertexShader;
    private Shader fragmentShader;

    private OpenGL openGL;

    public Window() {
        openGL = new OpenGL(width, height, title, clearColor);

        try {
			vertexShader = new Shader("./res/shaders/VertexShader.txt");
			fragmentShader = new Shader("./res/shaders/FragmentShader.txt");
		} catch (IOException e) {
            System.out.println("There was an error reading a shader.");
            System.exit(1);
		}

    }

    public void update() {
        openGL.update();
    }

    public void render(Object[] objects) {
        openGL.render(new float[0], new float[0]); // FIXME
    }

    public boolean isOpen() {
        return openGL.isOpen();
    }

    public void close() {
        openGL.close();
    }
    
}
