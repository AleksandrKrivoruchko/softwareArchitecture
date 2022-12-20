package domain.warehouse;

public interface IWorkWithData<T> {
    boolean add(T t);
    boolean remove(T t);
}
