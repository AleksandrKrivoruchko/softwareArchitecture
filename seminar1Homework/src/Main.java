import inMemoryModel.IModelChangedObserver;
import inMemoryModel.ModelStore;

public class Main {
    public static void main(String[] args) {
        ModelStore modelStore = new ModelStore();
        DemoObserver o = new DemoObserver();
        DemoObserver o1 = new DemoObserver();
        IModelChangedObserver o2 = new DemoObserver();

        modelStore.RegisterModelChanger(o);
        System.out.println();
        modelStore.RegisterModelChanger(o1);
        System.out.println();
        modelStore.RegisterModelChanger(o2);

        System.out.println();
        modelStore.RemoveModelChanger(o);
        System.out.println();
        modelStore.RemoveModelChanger(o2);
    }


}

class DemoObserver implements IModelChangedObserver {
    private int id;
    static int counter = 0;

    {
        id = ++counter;
    }
    @Override
    public void applyUpdateModel() {
        System.out.println("Observer " + id);
    }
}