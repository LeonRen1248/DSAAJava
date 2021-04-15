package chapter3.exercises;

/**
 * Exercise 3.6
 */
public class Exercise3_6 {
    private Node<Integer> marker;
    private int theSize;

    private static class Node<AnyType> {
        public AnyType data;
        public Node<AnyType> next;

        public Node(AnyType data, Node<AnyType> next) {
            this.data = data;
            this.next = next;
        }
    }

    public Exercise3_6() {
        marker = new Node<>(null, null);
        marker.next = marker;
        theSize = 0;
    }

    private void createCircularList(int N) {
        Node<Integer> node;
        for (int i = 1; i <= N; i++) {
            node = new Node<>(i, null);
            if (theSize == 0) {
                marker.next = node;
                node.next = node;
            } else {
                node.next = marker.next.next;
                marker.next.next = node;
                marker.next = node;
            }
            theSize++;
        }
    }

    public void printCircularList() {
        System.out.print("[ ");
        Node<Integer> node;
        for (node = marker.next.next; node != marker.next; node = node.next) {
            System.out.print(node.data + ", ");
        }
        System.out.print(node.data + ", ");
        System.out.print("]\n");
    }

    public int removeNextNode(Node<Integer> beforeDeleteNode) {
        Node<Integer> node = beforeDeleteNode.next;
        if (node == marker.next) {
            marker.next = beforeDeleteNode;
        }
        beforeDeleteNode.next = node.next;
        node.next = null;
        theSize--;
        return node.data;
    }

    public int josephusProblem(int M, int N) {
        createCircularList(N);
        int loopIdx = 0;
        Node<Integer> node = marker.next;
        while (theSize > 1) {
            if (loopIdx == M) {
                removeNextNode(node);
                loopIdx = 0;
            }
            else {
                node = node.next;
                loopIdx++;
            }
        }
        return marker.next.data;
    }

    public static void main(String[] args) {
        Exercise3_6 obj = new Exercise3_6();
        int ans = obj.josephusProblem(0, 10);
        System.out.println(ans);
    }
}
