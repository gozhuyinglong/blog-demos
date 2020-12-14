package io.github.gozhuyinglong.datastructures.linkedlist;

/**
 * 单链表
 *
 * @author 码农StayUp
 * @date 2020/11/20 0020
 */
public class SinglyLinkedListDemo {

    public static void main(String[] args) {
        Node node1 = new Node(1, "张三");
        Node node2 = new Node(3, "李四");
        Node node3 = new Node(7, "王五");
        Node node4 = new Node(5, "赵六");

        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        System.out.println("-----------添加节点（尾部）");
        singlyLinkedList.add(node1);
        singlyLinkedList.add(node2);
        singlyLinkedList.add(node3);
        singlyLinkedList.add(node4);
        singlyLinkedList.print();

        System.out.println("-----------获取某个节点");
        Node node = singlyLinkedList.get(3);
        System.out.println(node);

        singlyLinkedList.remove(node3);
        System.out.println("-----------移除节点");
        singlyLinkedList.print();

        System.out.println("-----------修改节点");
        singlyLinkedList.update(new Node(5, "赵六2"));
        singlyLinkedList.print();

        System.out.println("-----------按顺序添加节点");
        Node node5 = new Node(4, "王朝");
        singlyLinkedList.addOfOrder(node5);
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
         * 按顺序添加节点
         *
         * @param node
         */
        public void addOfOrder(Node node) {
            Node temp = head;
            while (true) {
                if (temp.next == null) {
                    temp.next = node;
                    break;
                } else if(temp.next.key > node.getKey()){
                    node.next = temp.next;
                    temp.next = node;
                    break;
                }
                temp = temp.next;
            }
        }

        /**
         * 获取某个节点
         *
         * @param key
         * @return
         */
        public Node get(int key) {
            if (head.next == null) {
                return null;
            }
            Node temp = head.next;
            while (temp != null) {
                if (temp.key == key) {
                    return temp;
                }
                temp = temp.next;
            }
            return null;
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
                if (temp.next.key == node.key) {
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
            Node temp = head.next;
            while (true) {
                if (temp == null) {
                    break;
                }
                if (temp.key == node.key) {
                    temp.value = node.value;
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
        private final int key;
        private String value;
        private Node next;

        public Node(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
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
