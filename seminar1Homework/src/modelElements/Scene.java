package modelElements;

import java.util.Collection;

public class Scene {
    public int id;
    static int counter = 0;
    public Collection<PoligonalModel> models;
    public Collection<Flash> flashes;
    {
        id = ++counter;
    }



}
