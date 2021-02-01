public class Player extends Object {

    public Player(float x, float y, float z) {
        super(x, y, z);
    }

    public Player(Tuple position) {
        super(position);
    }

    public Player(float x, float y, float z, Model model) {
        super(x, y, z, model);
    }

    public Player(Tuple position, Model model) {
        super(position, model);
    }
    
}
