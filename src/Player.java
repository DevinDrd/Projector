public class Player extends Object {

    public Player(float x, float y, float z) {
        super(x, y, z);
    }

    public Player(Triple position) {
        super(position);
    }

    public Player(float x, float y, float z, Model model) {
        super(x, y, z, model);
    }

    public Player(Triple position, Model model) {
        super(position, model);
    }
    
}
