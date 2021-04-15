package chapter3.exercises;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Exercise 3.4
 */
public class Exercise3_5 {
    public <AnyType> List<AnyType> union(List<AnyType> L1, List<AnyType> L2) {
        List<AnyType> res = new LinkedList<>(L1);
        for (AnyType value : L2) {
            if (!res.contains(value))
                res.add(value);
        }
        return res;
    }

    public static void main(String[] args) {
        Exercise3_5 obj = new Exercise3_5();
        LinkedList<String> L1 = new LinkedList<>();
        LinkedList<String> L2 = new LinkedList<>();
        L1.addAll(Arrays.asList("a", "bcde", "fg", "hij", "k", "lm", "n"));
        L2.addAll(Arrays.asList("bcde", "hij", "a", "jafklds", "jid", "jjj", "n"));
        System.out.println(obj.union(L1, L2));
    }
}
