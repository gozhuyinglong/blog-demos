package io.github.gozhuyinglong.datastructures.tree;

/**
 * B树
 *
 * @author ZhuYinglong
 * @date 2020/12/22 0022
 */
public class BTreeDemo {

    public static void main(String[] args) {

        Node node = new Node(0, "000");

        Node node1 = new Node(1, "a");
        Node node2 = new Node(3, "c");
        Node node3 = new Node(5, "e");
        Node node4 = new Node(2, "b");
        Node node5 = new Node(4, "d");
        node.addChild(node1);
        node.addChild(node2);
        node.addChild(node3);
        node.addChild(node4);
        node.addChild(node5);


        for (int i = 0; i < node.childNode.length; i++) {
            System.out.println(node.childNode[i]);
        }


    }

    private static class BTree {


        public void add(int key, String value) {
            Node node = new Node(key, value);
        }

    }

    private static class Node implements Comparable<Node> {

        private static final int childCapacity = 5; // 子节点容量

        private final int key; // 节点的键
        private final String value; // 节点的值
        private Node[] childNode = new Node[childCapacity]; // 子节点，使用数组存储子节点

        /**
         * @param key   节点的键
         * @param value 节点的值
         */
        public Node(int key, String value) {
            this.key = key;
            this.value = value;
        }

        /**
         * 添加一个子节点，并保证其顺序
         *
         * @param node
         */
        public void addChild(Node node) {
            Node[] temp = new Node[childCapacity];
            int i = 0;
            for (; i < childNode.length; i++) {
                if (childNode[i] == null) {
                    temp[i] = node;
                    break;
                }

                if (childNode[i].compareTo(node) > 0) {
                    temp[i] = node;
                    break;
                }
                temp[i] = childNode[i];
            }
            System.arraycopy(childNode, i, temp, i + 1, childCapacity - i - 1);
            childNode = temp;
        }

        /**
         * 获取子节点大小
         *
         * @return
         */
        public int getChildSize() {
            for (int i = 0; i < childNode.length; i++) {
                if (childNode[i] == null) {
                    return i;
                }

                if (i == childNode.length - 1) {
                    return childNode.length;
                }
            }
            return 0;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(key, o.key);
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
