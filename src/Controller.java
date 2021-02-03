import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

import Entity.*;
import Math.*;

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

            if (key == GLFW_KEY_W)
                player.addVelocity(new Vector(0, .1f, 0));
            else if (key == GLFW_KEY_A)
                player.addVelocity(new Vector(-.1f, 0, 0));
            else if (key == GLFW_KEY_S)
                player.addVelocity(new Vector(0, -.1f, 0));
            else if (key == GLFW_KEY_D)
                player.addVelocity(new Vector(.1f, 0, 0));
            else if (key == GLFW_KEY_SPACE);

            else if (key == GLFW_KEY_LEFT_SHIFT);

        }
        else if (event.action == GLFW_REPEAT) {

        }
        else if (event.action == GLFW_RELEASE) {
            if (key == GLFW_KEY_W)
                player.setVelocity(new Vector(0, 0, 0));
            else if (key == GLFW_KEY_A)
                player.setVelocity(new Vector(0, 0, 0));
            else if (key == GLFW_KEY_S)
                player.setVelocity(new Vector(0, 0, 0));
            else if (key == GLFW_KEY_D)
                player.setVelocity(new Vector(0, 0, 0));
            else if (key == GLFW_KEY_SPACE);

            else if (key == GLFW_KEY_LEFT_SHIFT);
        }
    }
    
}
