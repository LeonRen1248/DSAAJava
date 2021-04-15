package chapter3;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class MyLinkedList<AnyType> implements Iterable<AnyType> {
    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;

    private static class Node<AnyType> {
        AnyType data;
        Node<AnyType> prev;
        Node<AnyType> next;

        public Node(AnyType data, Node<AnyType> prev, Node<AnyType> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    public MyLinkedList() {
        clear();
    }

    public void clear() {
        theSize = 0;
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.next = endMarker;
        modCount++;
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return theSize == 0;
    }

    public boolean add(AnyType x) {
        add(theSize, x);
        return true;
    }

    public void add(int idx, AnyType x) {
        addBefore(getNode(idx), x);
    }

    public AnyType get(int idx) {
        return getNode(idx).data;
    }

    public AnyType set(int idx, AnyType newVal) {
        Node<AnyType> node = getNode(idx);
        AnyType oldVal = node.data;
        node.data = newVal;
        modCount++;
        return oldVal;
    }

    public AnyType remove(int idx) {
        return remove(getNode(idx));
    }

    private void addBefore(Node<AnyType> p, AnyType x) {
        Node<AnyType> addItem = new Node<>(x, p.prev, p);
        p.prev.next = addItem;
        p.prev = addItem;
        theSize++;
        modCount++;
    }

    private AnyType remove(Node<AnyType> p) {
        p.prev.next = p.next;
        p.next.prev = p.prev;
        modCount++;
        return p.data;
    }

    private Node<AnyType> getNode(int idx) {
        if (idx < 0 || idx > theSize)
            throw new IndexOutOfBoundsException();
        Node<AnyType> node;
        if (idx < theSize / 2) {
            node = beginMarker.next;
            for (int i = 0; i < idx; i++) {
                node = node.next;
            }
        }
        else {
            node = endMarker;
            for (int i = theSize; i > idx; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    public Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (Node<AnyType> node = beginMarker.next; node != endMarker; node = node.next) {
            sb.append(node.data + ", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private class LinkedListIterator implements Iterator<AnyType> {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        public boolean hasNext() {
            // 在这儿要将endMarker作为一个遍历节点，因为如果不加的话，删除最后一个节点就操作不了了
            return current != endMarker;
        }

        public AnyType next() {
            if (!hasNext())
                throw new IndexOutOfBoundsException();
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException();
            AnyType oldVal = current.data;
            current = current.next;
            okToRemove = true;
            return oldVal;
        }

        public void remove() {
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException();
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            MyLinkedList.this.remove(current.prev);
            okToRemove = false;
            expectedModCount++;
        }
    }

    /**
     * Unit test for MyLinkedList.
     * @param args
     */
    public static void main(String[] args) {
        MyLinkedList<Integer> mll = new MyLinkedList<>();
        // ==================== Add test - add(value); ====================
        for (int i = 10; i > 0; i--) {
            mll.add(i);
        }
        System.out.println("Initial array: " + mll);
        // ==================== Add test - add(idx, value); ====================
        for (int i = 1; i < 15; i += 3) {
            mll.add(i, 100 - i);
        }
        System.out.println("After add(idx, value) test: " + mll);
        // ==================== Add test - get(idx); ====================
        System.out.println("Get(idx) test: " + mll.get(1) + ", " + mll.get(5) + ", " + mll.get(mll.size() - 1));
        // ==================== Add test - set(idx, newVal); ====================
        mll.set(3, 1000);
        mll.set(mll.size() - 1, 222);
        System.out.println("After set(3, 1000) and set(size() - 1, 222) test: " + mll);
        // ==================== Add test - remove(idx); ====================
        mll.remove(0);
        mll.remove(3);
        System.out.println("After remove test: " + mll);
        // ==================== Add test - iterator; ====================
        mll.clear();
        for (int i = 10; i > 0; i--) {
            mll.add(i);
        }
        int sum = 0;
        for (int value : mll) {
            sum += value;
        }
        System.out.println("After clear() and iterator test(Calculate the sum): " + mll + " Sum is: " + sum);
    }
}
