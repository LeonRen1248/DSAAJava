import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tester {
    public static void main(String[] args) {
        ArrayList<Integer> al = new ArrayList<>();
        int N = 10;
        for (int i = N; i > 0; i--) {
            al.add(i);
        }
        System.out.println("Initial array: " + al);

        // Delete the odd-th elements. The index start from 0.
        Iterator<Integer> iter = al.iterator();
        iter.next();
        boolean tag = true;
        while (iter.hasNext()) {
            if (tag) {
                iter.remove();
                iter.next();
            }
            else {
                iter.next();
            }
            tag = !tag;
        }
        if (tag) {
            iter.remove();
        }
        System.out.println("Array after delete the odd-th elements: " + al);
    }
}
