import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

import Entity.*;

public class Controller {

    private int key;

    private Player player;

    public Controller(Player player) {
        this.player = player;
    }

    public void update(ArrayList<KeyEvent> events) {
        for (KeyEvent event:events)
            perform(event);
    }

    private void perform(KeyEvent event) {
        System.out.println(event);

        key = event.key;

        if (event.action == GLFW_PRESS) {

            if (key == GLFW_KEY_W);

            else if (key == GLFW_KEY_A);

            else if (key == GLFW_KEY_S);

            else if (key == GLFW_KEY_D);

            else if (key == GLFW_KEY_SPACE);

            else if (key == GLFW_KEY_LEFT_SHIFT);

        }
        else if (event.action == GLFW_REPEAT) {

        }
        else if (event.action == GLFW_RELEASE) {
            if (key == GLFW_KEY_W);

            else if (key == GLFW_KEY_A);

            else if (key == GLFW_KEY_S);

            else if (key == GLFW_KEY_D);

            else if (key == GLFW_KEY_SPACE);

            else if (key == GLFW_KEY_LEFT_SHIFT);
        }
    }
    
}
