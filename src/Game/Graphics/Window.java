package Game.Graphics;

import Game.Level;
import Game.Input.KeyEvent;
import Game.Math.Projection;
import Game.Util.BufferUtil;

import java.util.ArrayList;

import java.io.IOException;

public class Window {

    private int width = 800;
    private int height = 800;

    private String title = "Game";

    private float[] clearColor = new float[] {.05f, .05f, .2f, 1.0f};

    private Shader textureVertShader;
    private Shader textureFragShader;

    private String projUni = "MVP";
    private String texUni = "texture1";

    private OpenGL openGL;

    public Window() {
        openGL = new OpenGL(width, height, title, clearColor);

        try {
            textureVertShader = new Shader("./res/shaders/textureVertShader.txt");
			textureFragShader = new Shader("./res/shaders/textureFragShader.txt");
		} catch (IOException e) {
            System.out.println("There was an error reading a shader.");
            System.exit(1);
        }
        
        openGL.setShader(textureVertShader);
        openGL.setShader(textureFragShader);
    }

    public void addTextures(Level level) {
        for (TextureMap map:level.getTexMaps())
            openGL.addTexture(map);
    }

    public void update() {
        openGL.update();
    }

    public ArrayList<KeyEvent> getEvents() {
        return openGL.pollEvents();
    }

    public void render(Level level) {
        openGL.setUniMat4(projUni, level.getPlayers().get(0).getCamera().getProjection(Projection.PERSPECTIVE));
        openGL.setUniTex(texUni, level.getTexMaps().get(0).getSlot());
        openGL.render(BufferUtil.toFloats(level.getVertices()), BufferUtil.toFloats(level.getCoords()));
    }

    public boolean isOpen() {
        return openGL.isOpen();
    }

    public void close() {
        openGL.close();
    }
    
}
