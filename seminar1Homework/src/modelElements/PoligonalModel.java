package modelElements;

import java.util.ArrayList;
import java.util.Collection;

public class PoligonalModel {
    public Collection<Poligon> poligons = new ArrayList<>();
    public Collection<Texture> textures = new ArrayList<>();

    public Collection<Poligon> getPoligons() {
        return poligons;
    }

    public void setPoligons(Collection<Poligon> poligons) {
        this.poligons = poligons;
    }

    public Collection<Texture> getTextures() {
        return textures;
    }

    public void setTextures(Collection<Texture> textures) {
        this.textures = textures;
    }
}
