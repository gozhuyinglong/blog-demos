package com.github.gozhuyinglong.datastructures.linkedlist;

/**
 * 单链表
 *
 * @author ZhuYinglong
 * @date 2020/11/20 0020
 */
public class SinglyLinkedListDemo {

    public static void main(String[] args) {
        Node node1 = new Node(1, "张三");
        Node node2 = new Node(9, "李四");
        Node node3 = new Node(6, "王五");
        Node node4 = new Node(3, "王五");

        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        singlyLinkedList.add(node1);
        singlyLinkedList.add(node2);
        singlyLinkedList.add(node3);
        singlyLinkedList.add(node4);

        singlyLinkedList.print();

    }


    private static class SinglyLinkedList {

        // head节点是单链表的开始，不用来存储数据
        private Node head = new Node(0, null);

        /**
         * 将节点添加到尾部
         *
         * @param node
         */
        public void add(Node node) {
            Node temp = head;
            while (true) {
                if (temp.next == null) {
                    temp.next = node;
                    break;
                }
                temp = temp.next;
            }
        }

        /**
         * 移除一个节点
         *
         * @param node
         */
        public void remove(Node node) {
            Node temp = head;
            while (true) {
                if (temp.next == null) {
                    break;
                }
                if (temp.no == node.no) {
                    temp.next = temp.next.next;
                    break;
                }
                temp = temp.next;
            }
        }

        /**
         * 修改一个节点
         *
         * @param node
         */
        public void update(Node node) {
            Node temp = head;
            while (true) {
                if (temp.next == null) {
                    break;
                }
                if (temp.no == node.no) {
                    temp.name = node.name;
                    break;
                }
                temp = temp.next;
            }
        }

        /**
         * 打印链表
         */
        public void print() {
            Node temp = head.next;
            while (temp != null) {
                System.out.println(temp.toString());
                temp = temp.next;
            }
        }

    }


    private static class Node {
        private int no;
        private String name;
        private Node next;

        public Node(int no, String name) {
            this.no = no;
            this.name = name;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
