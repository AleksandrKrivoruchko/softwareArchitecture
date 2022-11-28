package modelElements;

public class Texture {
    private int id;
    static int counter = 0;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    {
        id = ++counter;
    }

    public Texture(String name) {
        this.name = name;
    }
}
