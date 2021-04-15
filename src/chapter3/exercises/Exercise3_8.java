package chapter3.exercises;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Exercise 3.8
 */
public class Exercise3_8 {
    public static void main(String[] args) {
        LinkedList<Integer> llst = new LinkedList<>();
        ArrayList<Integer> alst = new ArrayList<>();
        final int SIZE = 100000;
        createList(llst, SIZE);
        createList(alst, SIZE);

        System.out.println("==================== Test LinkedList ====================");
        long startTime = System.currentTimeMillis();
        removeFirstHalf2(llst);
        long endTime = System.currentTimeMillis();
        System.out.println("Cost: " + (endTime - startTime));

        System.out.println("==================== Test ArrayList ====================");
        startTime = System.currentTimeMillis();
        removeFirstHalf2(alst);
        endTime = System.currentTimeMillis();
        System.out.println("Cost: " + (endTime - startTime));
    }

    public static void createList(List<Integer> lst, int N) {
        for (int i = 0; i < N; i++)
            lst.add(i);
    }

    public static void removeFirstHalf(List<?> lst) {
        int theSize = lst.size() / 2;
        for (int i = 0; i < theSize; i++)
            lst.remove(0);
    }

    /**
     * Remove first half of list using iterator.
     * @param lst
     */
    public static void removeFirstHalf2(List<?> lst) {
        int theSize = lst.size() / 2;
        Iterator iter = lst.listIterator();
        for (int i = 0; i < theSize; i++) {
            iter.next();
            iter.remove();
        }
    }
}
