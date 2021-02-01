public class KeyEvent {

    public final int key;
    public final int action;

    public KeyEvent(int key, int action) {
        this.key = key;
        this.action = action;
    }

    public String toString() {
        String output = "";

        output += "Key: " + key;
        output += " | Action: " + action;

        return output;
    }

}
