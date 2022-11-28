package inMemoryModel;

import modelElements.Camera;
import modelElements.Flash;
import modelElements.PoligonalModel;
import modelElements.Scene;

import java.util.List;

public class ModelStore implements IModelChanger{
    public List<PoligonalModel> models;
    public List<Scene> scenes;
    public List<Flash> flashes;
    public List<Camera> cameras;
    private  List<IModelChangedObserver> changeObservers;

    public Scene getScene(int index) {
        return scenes.get(index);
    }

    @Override
    public void notifyChange(IModelChanger sender) {

    }
}
