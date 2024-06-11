package views;

import java.util.List;

public interface IView<T> {
    int view();
    void viewMessage(boolean result);
    void searchResultByName(List<T>items, String userType);
    void searchResultByCode(List<T>items, int userType);
    void printHeader();
    void displayAllItems(List<T>items);
    int inputCode();
    boolean confirmDelete(T item);
    String inputName();
    T viewEdit(T oldItem);
    int viewSearch();

}
