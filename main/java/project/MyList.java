package project;

public class MyList <T extends Comparable<T>> implements Listable<T> {

    private T[] list;
    private int count;

    public MyList() { }

    public MyList(int capacity){
        list = (T[]) new Comparable[capacity];
    }

    @Override
    public void add(T data) {
        if(count == list.length)
            resize();
        list[count++] = data;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public boolean delete(T data) {
        int index = find(data);
        if (index == -1) {
            System.out.println("Error : not found!!");
            return false;
        }
        for (int i = index+1; i < count; i++)
            list[i-1] = list[i];
        count--;
        return true;
    }

    @Override
    public boolean delete(int index) {
        if(index >= count)
            return false;
        for (int i = index+1; i < count; i++)
            list[i-1] = list[i];
        count--;
        return true;
    }

    @Override
    public int find(T data) {
        for (int i = 0; i < count; i++) {
            if(data.compareTo(list[i]) == 0)
                return i;
        }
        return -1;
    }

    @Override
    public T get(int index) {
        if(index >= count) {
            System.out.println("Error : Index out of bounds");
            return null;
        }
        return list[index];
    }

    @Override
    public void traverse() {
        System.out.println("index    data");
        System.out.println("--------------");
        for (int i = 0; i < count; i++) {
            System.out.println(i + "        " + list[i]);
        }
        System.out.println();
    }

    @Override
    public void clear() {
        count = 0;
    }

    @Override
    public void resize() {
        T[] newList = (T[]) new Comparable[list.length * 2];
        System.arraycopy(list,0, newList, 0, list.length);
        list = newList;
    }

    public int getCount() {
        return count;
    }
}