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

            if (key == GLFW_KEY_W) // move in direction of the camera
                player.addVelocity(Vector.divide(player.getCamera().getDirectionForward(), 10));

            else if (key == GLFW_KEY_A) // move in the direction to the right from the camera's perspective
                player.addVelocity(Vector.divide(player.getCamera().getDirectionRight(), -10)); // negative sign**

            else if (key == GLFW_KEY_S) // move in opposite direction of the camera
                player.addVelocity(Vector.divide(player.getCamera().getDirectionForward(), -10)); // negative sign**

            else if (key == GLFW_KEY_D) // move in the direction to the left from the camera's perspective
                player.addVelocity(Vector.divide(player.getCamera().getDirectionRight(), 10));

            else if (key == GLFW_KEY_SPACE)
                player.addVelocity(Vector.divide(player.getCamera().getDirectionUp(), 10));

            else if (key == GLFW_KEY_LEFT_SHIFT)
                player.addVelocity(Vector.divide(player.getCamera().getDirectionUp(), -10)); // negative sign**

            else if (key == GLFW_KEY_UP);

            else if (key == GLFW_KEY_DOWN);

            else if (key == GLFW_KEY_LEFT);

            else if (key == GLFW_KEY_RIGHT);
            
        }
        else if (event.action == GLFW_REPEAT) {

        }
        else if (event.action == GLFW_RELEASE) {
            if (key == GLFW_KEY_W) // move in direction of the camera
                player.addVelocity(Vector.divide(player.getCamera().getDirectionForward(), -10)); // negative sign**

            else if (key == GLFW_KEY_A) // move in the direction to the right from the camera's perspective
                player.addVelocity(Vector.divide(player.getCamera().getDirectionRight(), 10));

            else if (key == GLFW_KEY_S) // move in opposite direction of the camera
                player.addVelocity(Vector.divide(player.getCamera().getDirectionForward(), 10));

            else if (key == GLFW_KEY_D) // move in the direction to the left from the camera's perspective
                player.addVelocity(Vector.divide(player.getCamera().getDirectionRight(), -10)); // negative sign**

            else if (key == GLFW_KEY_SPACE)
                player.addVelocity(Vector.divide(player.getCamera().getDirectionUp(), -10)); // negative sign**

            else if (key == GLFW_KEY_LEFT_SHIFT)
                player.addVelocity(Vector.divide(player.getCamera().getDirectionUp(), 10));
        }
    }
    
}
