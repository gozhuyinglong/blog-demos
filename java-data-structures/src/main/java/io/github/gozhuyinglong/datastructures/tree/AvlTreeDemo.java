package io.github.gozhuyinglong.datastructures.tree;

/**
 * AVL树（平衡二叉树）
 *
 * @author ZhuYinglong
 * @date 2020/12/14 0014
 */
public class AvlTreeDemo {

    private static class AvlTree {
        private Node root; // 树根

        /**
         * 插入元素，并更新树根
         *
         * @param element
         */
        public void add(int element) {
            root = add(element, root);
        }

        /**
         * 递归插入元素，并对树做平衡
         *
         * @param element
         * @param node
         * @return
         */
        private Node add(int element, Node node) {
            if (node == null) {
                return new Node(element);
            }
            int compareResult = node.compareTo(element);
            if (compareResult > 0) {
                node.right = add(element, node.right);
            } else if (compareResult < 0) {
                node.left = add(element, node.left);
            } else {
                // 值相同，则不插入
            }
            return balance(node);
        }

        /**
         * 对树做平衡
         *
         * @param node
         * @return
         */
        private Node balance(Node node) {
            return null;
        }

        /**
         * 获取节点的高度，如果是空则高度为-1
         *
         * @param node
         * @return
         */
        private int height(Node node) {
            return node == null ? -1 : node.height;
        }

        /**
         * 左旋转
         *
         * @param k1 原树根
         * @return 新树根
         */
        private Node leftRotate(Node k1) {
            Node k2 = k1.right;
            k1.right = k2.left;
            k2.left = k1;
            k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
            k2.height = Math.max(k1.height, height(k2.right)) + 1;
            return k2;
        }

        /**
         * 右旋转
         *
         * @param k2 原树根
         * @return 新树根
         */
        private Node rightRotate(Node k2) {
            Node k1 = k2.left;
            k2.left = k1.right;
            k1.right = k2;
            k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
            k1.height = Math.max(height(k1.left), k2.height) + 1;
            return k1;
        }
    }

    private static class Node implements Comparable<Integer> {
        private final int element; // 数据元素
        private Node left; // 左子节点
        private Node right; // 右子节点
        private int height; // 树的高度

        public Node(int element) {
            this.element = element;
        }

        @Override
        public int compareTo(Integer o) {
            return o.compareTo(element);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    '}';
        }
    }
}
