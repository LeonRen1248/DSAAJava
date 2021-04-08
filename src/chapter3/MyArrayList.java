package chapter3;

import java.util.Iterator;

public class MyArrayList<AnyType> implements Iterator<AnyType> {
    private static final int DEFAULT_CAPACITY = 10;

    private int theSize;
    private AnyType[] theItems;

    public MyArrayList() {
    }

    public void clear() {
    }

    public int size() {
    }

    public boolean isEmpty() {
    }

    public void trimToSize() {
    }

    public AnyType get(int idx) {
    }

    public AnyType set(int idx, AnyType newVal) {
    }

    public void ensureCapacity(int newCapacity) {
    }

    public boolean add(AnyType x) {
    }

    public void add(int idx, AnyType x) {
    }

    public AnyType remove(int idx) {
    }

    public Iterator<AnyType> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<AnyType> {
        public boolean hasNext() {
        }

        public AnyType next() {
        }

        public void remove() {
        }
    }
}
