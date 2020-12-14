package io.github.gozhuyinglong.datastructures.linkedlist;

/**
 * 环形链表
 *
 * @author 码农StayUp
 * @date 2020/11/22 0022
 */
public class CircularLinkedListDemo {

    public static void main(String[] args) {

        CircularLinkedList circularLinkedList = new CircularLinkedList();

        System.out.println("-----------添加10个节点");
        for (int i = 1; i <= 10; i++) {
            circularLinkedList.add(new Node(i));
        }
        circularLinkedList.print();

        System.out.println("-----------按约瑟夫问题顺序出列");
        circularLinkedList.josephusProblem(3);

    }

    private static class CircularLinkedList {
        private Node first = null; // 头部节点，即第一个节点

        /**
         * 添加节点，并将新添加的节点的next指向头部，形成一个环形
         *
         * @param node
         * @return
         */
        public void add(Node node) {
            if (first == null) {
                first = node;
                first.next = first;
                return;
            }

            Node temp = first;
            while (true) {
                if (temp.next == null || temp.next == first) {
                    temp.next = node;
                    node.next = first;
                    break;
                }
                temp = temp.next;
            }
        }

        /**
         * 按约瑟夫问题顺序出列
         * 即从第1个元素开始报数，报到num时当前元素出列，然后重新从下一个元素开始报数，直至所有元素出列
         *
         * @param num 表示报几次数
         */
        public void josephusProblem(int num) {
            Node currentNode = first;
            // 将当前节点指向最后一个节点
            do {
                currentNode = currentNode.next;
            } while (currentNode.next != first);

            // 开始出列
            while (true) {
                // 当前节点要指向待出列节点的前一节点（双向环形队列不需要）
                for (int i = 0; i < num - 1; i++) {
                    currentNode = currentNode.next;
                }
                System.out.printf("%s\t", currentNode.next.no);
                if(currentNode.next == currentNode){
                    break;
                }
                currentNode.next = currentNode.next.next;
            }
        }

        /**
         * 输出节点
         */
        public void print() {
            if (first == null) {
                return;
            }

            Node temp = first;
            while (true) {
                System.out.printf("%s\t", temp.no);
                if (temp.next == first) {
                    break;
                }
                temp = temp.next;
            }
            System.out.println();
        }
    }

    private static class Node {
        private final int no;
        private Node next; // 指向下一节点

        public Node(int no) {
            this.no = no;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "no=" + no +
                    '}';
        }
    }
}
