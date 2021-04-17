package chapter3;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyArrayList<AnyType> implements Iterable<AnyType> {
    private static final int DEFAULT_CAPACITY = 4;

    private int theSize;
    private AnyType[] theItems;

    public MyArrayList() {
        clear();
    }

    public void clear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return theSize == 0;
    }

    public void trimToSize() {
        ensureCapacity(theSize);
    }

    public AnyType get(int idx) {
        if (idx < 0 || idx >= theSize) {
            throw new IndexOutOfBoundsException();
        }
        return theItems[idx];
    }

    public AnyType set(int idx, AnyType newVal) {
        if (idx < 0 || idx >= theSize) {
            throw new IndexOutOfBoundsException();
        }
        AnyType oldValue = theItems[idx];
        theItems[idx] = newVal;
        return oldValue;
    }

    public void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize) return;
        AnyType[] newItems = (AnyType[]) new Object[newCapacity];
        for (int i = 0; i < theSize; i++) {
            newItems[i] = theItems[i];
        }
        theItems = newItems;
    }

    public boolean add(AnyType x) {
        add(theSize, x);
        return true;
    }

    public void add(int idx, AnyType x) {
        if (idx < 0 || idx > theSize) {
            throw new IndexOutOfBoundsException();
        }
        if (theSize == theItems.length) {
            ensureCapacity(2 * theSize);
        }
        for (int i = theSize; i > idx; i--) {
            theItems[i] = theItems[i - 1];
        }
        theItems[idx] = x;
        theSize++;
    }

    public AnyType remove(int idx) {
        if (idx < 0 || idx >= theSize) {
            throw new IndexOutOfBoundsException();
        }
        AnyType oldValue = theItems[idx];
        for (int i = idx; i < theSize - 1; i++) {
            theItems[i] = theItems[i + 1];
        }
        theSize--;
        return oldValue;
    }

    /**
     * Exercise 3.9
     * @param items
     */
    public void addAll(Iterable<? extends AnyType> items) {
        for (AnyType value : items) {
            add(value);
        }
    }

    public Iterator<AnyType> iterator() {
        return new ArrayListIterator();
    }

    public ListIterator<AnyType> listIterator() {
        return new ArrayListIterator();
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(theItems, theSize));
    }

    private class ArrayListIterator implements ListIterator<AnyType> {
        int currentIdx = 0;
        boolean backward = false;

        @Override
        public boolean hasNext() {
            return currentIdx < theSize;
        }

        @Override
        public AnyType next() {
            if (!hasNext())
                throw new NoSuchElementException();
            backward = false;
            return theItems[currentIdx++];
        }

        @Override
        public boolean hasPrevious() {
            return currentIdx > 0;
        }

        @Override
        public AnyType previous() {
            if (!hasPrevious())
                throw new IndexOutOfBoundsException();
            backward = true;
            return theItems[--currentIdx];
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            if (backward)
                MyArrayList.this.remove(currentIdx);
            else
                MyArrayList.this.remove(--currentIdx);
        }

        @Override
        public void set(AnyType value) {
            MyArrayList.this.set(currentIdx, value);
        }

        @Override
        public void add(AnyType value) {
            MyArrayList.this.add(currentIdx++, value);
        }
    }

    // Unit test
    public static void main(String[] args) {
        MyArrayList<Double> mal = new MyArrayList<>();
        int N = 10;
        for (int i = N - 2; i > 0; i--) {
            mal.add(i + 0.5);
        }
        System.out.println("Initial MyArrayList and basic functions test: " + mal + " Size: " + mal.size());

        mal.clear();
        for (int i = N; i > 0; i--) {
            mal.add(i + 0.5);
        }
        System.out.println("After clear() test: " + mal + " Size: " + mal.size());

        mal.set(2, 100000000.0);
        System.out.println("After set() test: " + mal + " Size: " + mal.size());

        System.out.println("After get() test: " + mal + " Size: " + mal.size() + ", Get the 10-th element: " + mal.get(9));

        mal.add(0, 100000000.0);
        mal.add(100.0);
        mal.add(1000.0);
        mal.add(5, 10000.0);
        System.out.println("After add() test: " + mal + " Size: " + mal.size());

        mal.remove(10);
        mal.remove(10);
        System.out.println("After remove() test: " + mal + " Size: " + mal.size());

        // Re-Initialize mal
        mal.clear();
        for (int i = N; i > 0; i--) {
            mal.add(i + 0.5);
        }
        double sum = 0;
        for (double item : mal) {
            sum += item;
        }
        System.out.println("After iterator test: " + mal + " The sum is: " + sum);

        // addAll test
        MyArrayList<Double> mal2 = new MyArrayList<>();
        for (int i = N; i > 0; i--) {
            mal2.add(i + 0.5);
        }
        mal.addAll(mal2);
        System.out.println("After addAll test: " + mal);

        // ListIterator test
        System.out.println("==================== ListIterator Test ====================");

    }
}
