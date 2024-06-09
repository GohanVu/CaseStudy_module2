package services;

import models.Product;

import java.util.List;

public interface IService <T extends Product> {
    boolean add(T t);
    List<T> getAll();
    T findByCode(int code);
    void removeProduct(T t);
    boolean update(T t);
    List<T> searchByName(String name);
    List<T> searchByCode(int code);
    boolean codeExists(int code);
}
