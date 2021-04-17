import chapter3.MyArrayList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Tester {
    public static void main(String[] args) {
        MyArrayList<Integer> al = new MyArrayList<>();
        int N = 10;
        for (int i = N; i > 0; i--) {
            al.add(i);
        }
        System.out.println("Initial array: " + al);

        ListIterator<Integer> iter = al.listIterator();
        iter.next();
        iter.next();
        iter.next();
        iter.previous();
        iter.add(15);
        iter.add(16);
        System.out.println("After delete one element using iterator: " + al);
        iter.previous();
        iter.previous();
        iter.remove();
        System.out.println(iter.previous());
        System.out.println("After delete one element using iterator: " + al);
//        System.out.println("Previous index is: " + iter.previousIndex());
        iter.previous();
        iter.remove();
        System.out.println("After add one element: " + al);
    }
}
