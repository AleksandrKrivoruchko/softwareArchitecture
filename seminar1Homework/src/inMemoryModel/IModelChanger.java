package inMemoryModel;

public interface IModelChanger {
    void RegisterModelChanger(IModelChangedObserver o);
    void RemoveModelChanger(IModelChangedObserver o);
    void notifyChange();
}
