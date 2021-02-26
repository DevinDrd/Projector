package Game.Input;

import Game.Entity.Hard.Player;
import Game.Math.Motion;
import Game.Math.Vector;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

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
                player.move(Motion.FORWARD);

            else if (key == GLFW_KEY_A) // move in the direction to the right from the camera's perspective
                player.move(Motion.LEFT);

            else if (key == GLFW_KEY_S) // move in opposite direction of the camera
                player.move(Motion.BACKWARD);

            else if (key == GLFW_KEY_D) // move in the direction to the left from the camera's perspective
                player.move(Motion.RIGHT);

            else if (key == GLFW_KEY_SPACE)
                player.move(Motion.UP);

            else if (key == GLFW_KEY_LEFT_SHIFT)
                player.move(Motion.DOWN);

            else if (key == GLFW_KEY_UP)
                player.rotate(Motion.SPINUP);

            else if (key == GLFW_KEY_DOWN)
                player.rotate(Motion.SPINDOWN);

            else if (key == GLFW_KEY_LEFT)
                player.rotate(Motion.SPINLEFT);

            else if (key == GLFW_KEY_RIGHT)
                player.rotate(Motion.SPINRIGHT);

            else if (key == GLFW_KEY_COMMA)
                player.rotate(Motion.COUNTERCLOCKWISE);

            else if (key == GLFW_KEY_PERIOD)
                player.rotate(Motion.CLOCKWISE);

            else if (key == GLFW_KEY_ENTER)
                player.getCamera().level();

            else if (key == GLFW_KEY_LEFT_CONTROL) // FIXME
                player.setVelocity(Vector.multiply(player.getVelocity(), 2));

        }
        else if (event.action == GLFW_REPEAT) {
            if (key == GLFW_KEY_W);

            else if (key == GLFW_KEY_A);

            else if (key == GLFW_KEY_S);

            else if (key == GLFW_KEY_D);

            else if (key == GLFW_KEY_SPACE);

            else if (key == GLFW_KEY_LEFT_SHIFT);

            else if (key == GLFW_KEY_UP)
                player.rotate(Motion.SPINUP);

            else if (key == GLFW_KEY_DOWN)
                player.rotate(Motion.SPINDOWN);

            else if (key == GLFW_KEY_LEFT)
                player.rotate(Motion.SPINLEFT);

            else if (key == GLFW_KEY_RIGHT)
                player.rotate(Motion.SPINRIGHT);

            else if (key == GLFW_KEY_COMMA)
                player.rotate(Motion.COUNTERCLOCKWISE);

            else if (key == GLFW_KEY_PERIOD)
                player.rotate(Motion.CLOCKWISE);

            else if (key == GLFW_KEY_LEFT_CONTROL);

                
        }
        else if (event.action == GLFW_RELEASE) {
            if (key == GLFW_KEY_W) // stop movement
                player.stopMove(Motion.FORWARD);

            else if (key == GLFW_KEY_A) // stop movement
                player.stopMove(Motion.LEFT);

            else if (key == GLFW_KEY_S) // stop movement
                player.stopMove(Motion.BACKWARD);

            else if (key == GLFW_KEY_D) // stop movement
                player.stopMove(Motion.RIGHT);

            else if (key == GLFW_KEY_SPACE)
                player.stopMove(Motion.UP);

            else if (key == GLFW_KEY_LEFT_SHIFT)
                player.stopMove(Motion.DOWN);

            else if (key == GLFW_KEY_UP);

            else if (key == GLFW_KEY_DOWN);

            else if (key == GLFW_KEY_LEFT);

            else if (key == GLFW_KEY_RIGHT);

            else if (key == GLFW_KEY_LEFT_CONTROL)
                player.setVelocity(Vector.multiply(player.getVelocity(), .5f));
        }
    }
    
}
