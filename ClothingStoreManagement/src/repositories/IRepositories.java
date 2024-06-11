package repositories;

import java.util.List;

public interface IRepositories<T> {
    void  add(T item);
    List<T> getAll();
    T findByCode(int code);
    void remove(T item);
    void update(T updateItem);
}
