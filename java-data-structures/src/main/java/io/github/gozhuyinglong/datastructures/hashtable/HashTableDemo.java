package io.github.gozhuyinglong.datastructures.hashtable;

/**
 * 散列表
 *
 * @author 码农StayUp
 * @date 2021/1/18 0018
 */
public class HashTableDemo {

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(10);
        System.out.println("------------初始化散列表");
        hashTable.print();

        System.out.println("------------添加关键字 12");
        hashTable.add(12);
        hashTable.print();

        System.out.println("------------添加关键字 13");
        hashTable.add(13);
        hashTable.print();

        System.out.println("------------添加关键字 24");
        hashTable.add(24);
        hashTable.print();

        System.out.println("------------添加关键字 34");
        hashTable.add(34);
        hashTable.print();

        System.out.println("------------添加关键字 44");
        hashTable.add(44);
        hashTable.print();
    }

    private static class HashTable {

        private final int size; // 散列表大小
        private final Node[] table; // 散列表

        private HashTable(int size) {
            this.size = size;
            this.table = new Node[size];
        }

        /**
         * 散列函数 - 除留余数法
         *
         * @param key
         * @return
         */
        private int hash(int key) {
            return key % size;
        }

        /**
         * 添加关键字
         *
         * @param key
         */
        public void add(int key) {
            int hashAddress = hash(key);
            if (table[hashAddress] == null) {
                table[hashAddress] = new Node(key);
                return;
            }
            add(table[hashAddress], key);
        }

        /**
         * 添加关键字 - 递归
         *
         * @param node
         * @param key
         */
        private void add(Node node, int key) {
            if (node.getNext() == null) {
                node.setNext(new Node(key));
                return;
            }
            add(node.getNext(), key);
        }

        /**
         * 匹配关键字
         *
         * @param key
         * @return
         */
        public boolean contains(int key) {
            int hashAddress = hash(key);
            Node headNode = table[hashAddress];
            if (headNode == null) {
                return false;
            }

            return contains(headNode, key);
        }

        /**
         * 匹配关键字 - 递归
         *
         * @param node
         * @param key
         * @return
         */
        private boolean contains(Node node, int key) {
            if (node == null) {
                return false;
            }
            if (node.getKey() == key) {
                return true;
            }
            return contains(node.getNext(), key);
        }

        /**
         * 移除关键字
         *
         * @param key
         */
        public void remove(int key) {
            int hashAddress = hash(key);
            Node headNode = table[hashAddress];
            if (headNode == null) {
                return;
            }
            if (headNode.getKey() == key) {
                table[hashAddress] = headNode.getNext();
                return;
            }
            remove(headNode, key);
        }

        /**
         * 移除关键字 - 递归
         *
         * @param node
         * @param key
         */
        private void remove(Node node, int key) {
            if (node.getNext() == null) {
                return;
            }
            if (node.getNext().getKey() == key) {
                node.setNext(node.getNext().getNext());
                return;
            }
            remove(node.getNext(), key);
        }

        /**
         * 打印散列表
         */
        public void print() {
            for (int i = 0; i < table.length; i++) {
                System.out.printf("[%s]\t", i);
                if (table[i] != null) {
                    print(table[i]);
                }
                System.out.println();
            }
        }

        private void print(Node node) {
            if (node == null) {
                return;
            }
            System.out.printf("%s\t", node.key);
            print(node.getNext());
        }

    }


    private static class Node {
        private final int key; // 关键字
        private Node next; // 下一节点

        public Node(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
