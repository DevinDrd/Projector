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

            if (key == GLFW_KEY_W) // move in direction of the camera
                player.forwardForce(player.getForce());

            else if (key == GLFW_KEY_A) // move in the direction to the right from the camera's perspective
                player.leftForce(player.getForce());

            else if (key == GLFW_KEY_S) // move in opposite direction of the camera
                player.backwardForce(player.getForce());

            else if (key == GLFW_KEY_D) // move in the direction to the left from the camera's perspective
                player.rightForce(player.getForce());

            else if (key == GLFW_KEY_SPACE)
                player.upForce(player.getForce());

            else if (key == GLFW_KEY_LEFT_SHIFT)
                player.downForce(player.getForce());

            else if (key == GLFW_KEY_UP)
                player.lookUp(15);

            else if (key == GLFW_KEY_DOWN)
                player.lookDown(15);

            else if (key == GLFW_KEY_LEFT)
                player.lookLeft(15);

            else if (key == GLFW_KEY_RIGHT)
                player.lookRight(15);

            else if (key == GLFW_KEY_LEFT_CONTROL)
                player.setForce(0.5f);

            else if (key == GLFW_KEY_COMMA)
                player.spinCounterclockwise(15);

            else if (key == GLFW_KEY_PERIOD)
                player.spingClockwise(15);

        }
        else if (event.action == GLFW_REPEAT) {
            if (key == GLFW_KEY_W);

            else if (key == GLFW_KEY_A);

            else if (key == GLFW_KEY_S);

            else if (key == GLFW_KEY_D);

            else if (key == GLFW_KEY_SPACE);

            else if (key == GLFW_KEY_LEFT_SHIFT);

            else if (key == GLFW_KEY_UP);

            else if (key == GLFW_KEY_DOWN);

            else if (key == GLFW_KEY_LEFT);

            else if (key == GLFW_KEY_RIGHT);

            else if (key == GLFW_KEY_LEFT_CONTROL);
                
        }
        else if (event.action == GLFW_RELEASE) {
            if (key == GLFW_KEY_W) // stop movement
                player.forwardForce(-player.getForce());

            else if (key == GLFW_KEY_A) // stop movement
                player.leftForce(-player.getForce());

            else if (key == GLFW_KEY_S) // stop movement
                player.backwardForce(-player.getForce());

            else if (key == GLFW_KEY_D) // stop movement
                player.rightForce(-player.getForce());

            else if (key == GLFW_KEY_SPACE) // stop movement
                player.upForce(-player.getForce());

            else if (key == GLFW_KEY_LEFT_SHIFT) // stop movement
                player.downForce(-player.getForce());

            else if (key == GLFW_KEY_UP);

            else if (key == GLFW_KEY_DOWN);

            else if (key == GLFW_KEY_LEFT);

            else if (key == GLFW_KEY_RIGHT);

            else if (key == GLFW_KEY_LEFT_CONTROL)
                player.setForce(0.1f);
        }
    }
    
}
