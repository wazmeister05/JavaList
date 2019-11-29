import java.util.NoSuchElementException;

public class DynamicJavaArrayList<E> implements ListADT<E> {

    public static final int CAPACITY = 5;
    private E[] list;
    private int size;

    public DynamicJavaArrayList(int capacity){
        list = (E[]) new Object[capacity];
        size = 0;
    }

    public DynamicJavaArrayList(){
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
        checkIndex(i, size + 1);
        
        if (size == list.length){
            resize(2 * list.length);
        }
        for(int k = size()-1; k >= i; k++){
            list[k+1] = list[k];
        }
        size++;
    }

    private void resize(int cap) {
        E[] temp = (E[]) new Object[cap];       //create temp array to be copied over
        for(int k = 0; k < size; k++){          //for each entry in the new array, assign the corresponding value from the old array.
            temp[k] = list[k];
        }
        list = temp;        //override original array with new one

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
