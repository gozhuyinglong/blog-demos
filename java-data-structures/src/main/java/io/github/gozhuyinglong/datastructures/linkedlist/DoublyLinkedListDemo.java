package io.github.gozhuyinglong.datastructures.linkedlist;

/**
 * 双链表
 *
 * @author 码农StayUp
 * @date 2020/11/21 0021
 */
public class DoublyLinkedListDemo {

    public static void main(String[] args) {

        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();

        System.out.println("-----------从尾部添加节点");
        doublyLinkedList
                .addToTail(new Node(1, "张三"))
                .addToTail(new Node(3, "李四"))
                .addToTail(new Node(7, "王五"))
                .addToTail(new Node(5, "赵六"))
                .print();

        System.out.println("-----------从头部添加节点");
        doublyLinkedList
                .addToHead(new Node(0, "朱开山"))
                .print();

        System.out.println("-----------获取某个节点");
        System.out.println(doublyLinkedList.get(3));

        System.out.println("-----------移除节点");
        doublyLinkedList
                .remove(new Node(3, "李四"))
                .print();

        System.out.println("-----------修改节点");
        doublyLinkedList
                .update(new Node(5, "赵六2")).print();

        System.out.println("-----------按顺序添加节点");
        doublyLinkedList
                .addOfOrder(new Node(4, "王朝"))
                .print();
    }

    private static class DoublyLinkedList {

        private Node first = null; // first节点是双链表的头部，即第一个节点
        private Node last = null; // tail节点是双链表的尾部，即最后一个节点

        /**
         * 从尾部添加
         *
         * @param node
         */
        public DoublyLinkedList addToTail(Node node) {
            if (last == null) {
                first = node;
            } else {
                last.next = node;
                node.prev = last;
            }
            last = node;
            return this;
        }

        /**
         * 按照顺序添加
         *
         * @param node
         */
        public DoublyLinkedList addOfOrder(Node node) {
            if (first == null) {
                first = node;
                last = node;
                return this;
            }

            // node比头节点小，将node设为头节点
            if (first.key > node.key) {
                first.prev = node;
                node.next = first;
                first = node;
                return this;
            }

            // node比尾节点大，将node设为尾节点
            if (last.key < node.key) {
                last.next = node;
                node.prev = last;
                last = node;
                return this;
            }

            Node temp = first.next;
            while (true) {
                if (temp.key > node.key) {
                    node.next = temp;
                    node.prev = temp.prev;
                    temp.prev.next = node;
                    temp.prev = node;
                    break;
                }
                temp = temp.next;
            }
            return this;
        }

        /**
         * 从头部添加
         *
         * @param node
         */
        public DoublyLinkedList addToHead(Node node) {
            if (first == null) {
                last = node;
            } else {
                node.next = first;
                first.prev = node;
            }
            first = node;
            return this;
        }

        /**
         * 获取节点
         *
         * @param key
         * @return
         */
        public Node get(int key) {
            if (first == null) {
                return null;
            }
            Node temp = first;
            while (temp != null) {
                if (temp.key == key) {
                    return temp;
                }
                temp = temp.next;
            }
            return null;
        }

        /**
         * 移除节点
         *
         * @param node
         */
        public DoublyLinkedList remove(Node node) {
            if (first == null) {
                return this;
            }
            // 要移除的是头节点
            if (first == node) {
                first.next.prev = null;
                first = first.next;
                return this;
            }
            // 要移除的是尾节点
            if (last == node) {
                last.prev.next = null;
                last = last.prev;
                return this;
            }

            Node temp = first.next;
            while (temp != null) {
                if (temp.key == node.key) {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                    break;
                }
                temp = temp.next;
            }
            return this;
        }

        /**
         * 修改某个节点
         *
         * @param node
         */
        public DoublyLinkedList update(Node node) {
            if (first == null) {
                return this;
            }

            Node temp = first;
            while (temp != null) {
                if (temp.key == node.key) {
                    temp.value = node.value;
                    break;
                }
                temp = temp.next;
            }
            return this;
        }

        /**
         * 打印链表
         */
        public void print() {
            if (first == null) {
                return;
            }
            Node temp = first;
            while (temp != null) {
                System.out.println(temp);
                temp = temp.next;
            }
        }
    }

    private static class Node {
        private final int key;
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
