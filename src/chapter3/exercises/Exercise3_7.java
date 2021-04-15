package chapter3.exercises;


import java.util.ArrayList;
import java.util.List;

/**
 * Exercise 3.7
 */
public class Exercise3_7 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        makeList(10000);
        long end = System.currentTimeMillis();
        System.out.println("Cost: " + (end - start));
    }

    public static List<Integer> makeList(int N) {
        ArrayList<Integer> lst = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            lst.add(i);
            lst.trimToSize();
        }
        return lst;
    }
}
