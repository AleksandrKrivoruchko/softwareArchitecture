package modelElements;

import java.util.Collection;
import java.util.List;

public class Poligon {
    public Collection<Point3D> points;

    public Collection<Point3D> getPoints() {
        return points;
    }

    public void setPoints(Collection<Point3D> points) {
        if(points.size() < 3) {
            throw new RuntimeException("Количество точек для полигона не дожно быть меньше 3!");
        }
        this.points = points;
    }

}
