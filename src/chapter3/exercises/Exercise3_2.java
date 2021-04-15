package chapter3.exercises;

/**
 * Exercise 3.2:
 * Exchange two element in linked-list only by modifying links.
 */
public class Exercise3_2 {
    private static class SingleLinkedList<AnyType> {
        private SingleNode<AnyType> sn;

        private static class SingleNode<AnyType> {
            AnyType data;
            SingleNode<AnyType> next;

            public SingleNode(AnyType data, SingleNode<AnyType> next) {
                this.data = data;
                this.next = next;
            }
        }

        public SingleLinkedList() {
            this.sn = new SingleNode<>(null, null);
        }

        public SingleLinkedList(AnyType[] items) {
            this();
            SingleNode<AnyType> node = this.sn;
            for (int i = 0; i < items.length; i++) {
                node.next = new SingleNode<>(items[i], null);
                node = node.next;
            }
        }

        /**
         * Exchange the idx-th and (idx+1)-th elements (Based 0-started counting) ONLY BY MODIFYING LINKS.
         *
         * @param idx
         */
        public void exchangeTwoNeighborElements(int idx) {
            SingleNode<AnyType> node = this.sn.next;
            for (int i = 0; i < idx - 1; i++) {
                node = node.next;
            }
            SingleNode<AnyType> node2 = node.next;
            node.next = node2.next;
            node2.next = node2.next.next;
            node.next.next = node2;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[ ");
            for (SingleNode<AnyType> node = this.sn.next; node != null; node = node.next) {
                sb.append(node.data + ", ");
            }
            sb.append("]");
            return sb.toString();
        }
    }

    private static class DoubleLinkedList<AnyType> {
        private DoubleNode<AnyType> beginMarker;
        private DoubleNode<AnyType> endMarker;

        private static class DoubleNode<AnyType> {
            AnyType data;
            DoubleNode<AnyType> prev;
            DoubleNode<AnyType> next;

            public DoubleNode(AnyType data, DoubleNode<AnyType> prev, DoubleNode<AnyType> next) {
                this.data = data;
                this.prev = prev;
                this.next = next;
            }
        }

        public DoubleLinkedList() {
            beginMarker = new DoubleNode<>(null, null, null);
            endMarker = new DoubleNode<>(null, beginMarker, null);
            beginMarker.next = endMarker;
        }

        public DoubleLinkedList(AnyType[] items) {
            this();
            DoubleNode<AnyType> node = endMarker;
            for (int i = 0; i < items.length; i++) {
                addNodeBefore(node, items[i]);
            }
        }

        private void addNodeBefore(DoubleNode<AnyType> node, AnyType data) {
            DoubleNode<AnyType> newNode = new DoubleNode<>(data, node.prev, node);
            node.prev.next = newNode;
            node.prev = newNode;
        }

        /**
         * Exchange the idx-th and (idx+1)-th elements (Based 0-started counting) ONLY BY MODIFYING LINKS.
         *
         * @param idx
         */
        public void exchangeTwoNeighborElements(int idx) {
            DoubleNode<AnyType> node = beginMarker.next;
            for (int i = 0; i < idx; i++) {
                node = node.next;
            }
            DoubleNode<AnyType> node2 = node.next;
            node.prev.next = node2;
            node2.prev = node.prev;
            node.next = node2.next;
            node2.next.prev = node;
            node.prev = node2;
            node2.next = node;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[ ");
            for (DoubleNode<AnyType> node = beginMarker.next; node != endMarker; node = node.next) {
                sb.append(node.data + ", ");
            }
            sb.append("]");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Integer[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10};

        // ==================== Single Linked List ====================
        System.out.println("==================== Single Linked List ====================");
        SingleLinkedList<Integer> sll = new SingleLinkedList<>(values);
        System.out.println("Initial single linked-list: " + sll);

        // Exchange
        sll.exchangeTwoNeighborElements(3);
        sll.exchangeTwoNeighborElements(6);
        System.out.println("After exchange 3rd and 4th elements: " + sll);
        System.out.println();

        // ==================== Double Linked List ====================
        System.out.println("==================== Double Linked List ====================");
        DoubleLinkedList<Integer> dll = new DoubleLinkedList<>(values);
        System.out.println("Initial double linked-list: " + dll);

        // Exchange
        dll.exchangeTwoNeighborElements(3);
        dll.exchangeTwoNeighborElements(7);
        System.out.println("After exchange 3rd and 4th elements: " + dll);
    }
}
