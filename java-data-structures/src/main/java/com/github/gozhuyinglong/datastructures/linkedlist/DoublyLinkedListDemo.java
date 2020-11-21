package com.github.gozhuyinglong.datastructures.linkedlist;

/**
 * 双链表
 *
 * @author ZhuYinglong
 * @date 2020/11/21 0021
 */
public class DoublyLinkedListDemo {

    public static void main(String[] args) {

        Node nodeA = new Node(1, "张三");
        Node nodeB = new Node(3, "李四");
        Node nodeC = new Node(7, "王五");
        Node nodeD = new Node(5, "赵六");
        System.out.println("-----------从头部添加节点");
        DoublyLinkedList doublyLinkedList2 = new DoublyLinkedList();
        doublyLinkedList2.addToHead(nodeA);
        doublyLinkedList2.addToHead(nodeB);
        doublyLinkedList2.addToHead(nodeC);
        doublyLinkedList2.addToHead(nodeD);
        doublyLinkedList2.print();

        Node node1 = new Node(1, "张三");
        Node node2 = new Node(3, "李四");
        Node node3 = new Node(7, "王五");
        Node node4 = new Node(5, "赵六");
        System.out.println("-----------从尾部添加节点");
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        doublyLinkedList.addToTail(node1);
        doublyLinkedList.addToTail(node2);
        doublyLinkedList.addToTail(node3);
        doublyLinkedList.addToTail(node4);
        doublyLinkedList.print();

    }

    private static class DoublyLinkedList {

        private Node head = null; // head节点是双链表的头部，即第一个节点
        private Node tail = null; // tail节点是双链表的尾部，即最后一个节点

        /**
         * 从尾部添加
         *
         * @param node
         */
        public void addToTail(Node node) {
            if (tail == null) {
                head = node;
            } else {
                tail.next = node;
                node.prev = tail;
            }
            tail = node;
        }

        /**
         * 从头部添加
         *
         * @param node
         */
        public void addToHead(Node node) {
            if (head == null) {
                tail = node;
            } else {
                node.next = head;
                head.prev = node;
            }
            head = node;
        }

        /**
         * 打印链表
         */
        public void print() {
            if (head == null) {
                return;
            }
            Node temp = head;
            while (temp != null) {
                System.out.println(temp);
                temp = temp.next;
            }
        }
    }

    private static class Node {
        private int key;
        private String value;
        private Node prev; // 指向上一节点
        private Node next; // 指向下一节点

        public Node(int key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
}
