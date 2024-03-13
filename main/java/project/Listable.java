package project;

public interface Listable <T extends Comparable<T>> {
    boolean isEmpty();
    void add(T data);
    boolean delete(T data);
    boolean delete(int index);
    int find(T data);
    T get(int index);
    void traverse();
    void clear();
    void resize();
}
