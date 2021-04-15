package chapter3.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Exercise 3.1
 */
public class Exercise3_1 {
    public static void main(String[] args) {
        Exercise3_1 obj = new Exercise3_1();
        ArrayList<Integer> L = new ArrayList<>();
        ArrayList<Integer> P = new ArrayList<>();
        L.addAll(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 1));
        P.addAll(Arrays.asList(1, 4, 6, 9));
        System.out.println("List L is: " + L);
        System.out.println("List P is: " + P);
        System.out.println("Result is: " + obj.printLots(L, P));
    }

    public List<Integer> printLots(List<Integer> L, List<Integer> P) {
        // Complexity: O(n)
        List<Integer> ans = new ArrayList<>();
        for (int idx : P) {
            ans.add(L.get(idx));
        }
        return ans;
    }
}
