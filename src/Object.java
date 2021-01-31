public class Object {

    private Triple position;
    private Triple velocity;

    private Model model;

    public Object(float x, float y, float z) {
        position = new Triple(x, y, z);
        velocity = new Triple(0, 0, 0);

        model = new Model(new float[] {}, new float[] {});
    }

    public Object(Triple position) {
        this.position = position;
        velocity = new Triple(0, 0, 0);

        model = new Model(new float[] {}, new float[] {});
    }

    public Object(float x, float y, float z, Model model) {
        position = new Triple(x, y, z);
        velocity = new Triple(0, 0, 0);

        this.model = model;
    }

    public Object(Triple position, Model model) {
        this.position = position;
        velocity = new Triple(0, 0, 0);

        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public String toString() {
        String output = "Object:\n";
        output += "position: " + position + "\n";
        output += "velocity: " + velocity + "\n";
        output += model;
        return output;
    }
    
}