package inMemoryModel;

import modelElements.Camera;
import modelElements.Flash;
import modelElements.PoligonalModel;
import modelElements.Scene;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ModelStore implements IModelChanger{
    public Collection<PoligonalModel> models = new ArrayList<>();
    public List<Scene> scenes = new ArrayList<>();
    public Collection<Flash> flashes = new ArrayList<>();
    public Collection<Camera> cameras = new ArrayList<>();
    private  Collection<IModelChangedObserver> changeObservers = new ArrayList<>();

    public Scene getScene(int index) {
        return scenes.get(index);
    }

    @Override
    public void RegisterModelChanger(IModelChangedObserver o) {
        changeObservers.add(o);
        notifyChange();
    }

    @Override
    public void RemoveModelChanger(IModelChangedObserver o) {
        changeObservers.remove(o);
        notifyChange();
    }

    @Override
    public void notifyChange() {
        for (IModelChangedObserver o : changeObservers) {
            o.applyUpdateModel();
        }
    }
}
