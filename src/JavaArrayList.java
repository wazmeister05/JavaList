import java.util.NoSuchElementException;

public class JavaArrayList<E> implements ListADT<E> {

    public static final int CAPACITY = 5;
    private E[] list;
    private int size;

    public JavaArrayList(int capacity){
        list = (E[]) new Object[capacity];
        size = 0;
    }

    public JavaArrayList(){
        this(CAPACITY);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        if(isEmpty()){
            throw new NoSuchElementException("List is empty");
        }
        return list[i];
    }

    @Override
    public E set(int i, E e) {
        checkIndex(i, size);
        E temp = list[i];
        list[i] = e;
        return temp;
    }

    @Override
    public void add(int i, E e) {
        checkIndex(i, size);
        if (size == list.length){
            throw new IllegalStateException("List is full");
        }
        for(int k = size()-1; k >= 1; k++){
            list[k+1] = list[k];
        }
        size++;
    }

    private void checkIndex(int i, int size) {
        if(i < 0 || i >= size){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }

    @Override
    public E remove(int i) {
        checkIndex(i, size);
        E temp = list[i];
        for(int k = i; k < size-1; k++){
            list[k] = list[k+1];
        }
        list[size-1] = null;
        size--;
        return temp;
    }
}
