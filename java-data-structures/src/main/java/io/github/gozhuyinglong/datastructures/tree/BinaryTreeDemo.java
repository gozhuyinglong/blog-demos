package io.github.gozhuyinglong.datastructures.tree;

/**
 * 二叉树
 *
 * @author 码农StayUp
 * @date 2020/11/30 0030
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();

        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Node f = new Node("F");
        Node g = new Node("G");
        Node h = new Node("H");
        Node i = new Node("I");

        tree.setRoot(a);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        d.left = h;
        c.left = f;
        c.right = g;
        g.right = i;

        System.out.println("---------------前序遍历");
        tree.preOrder();
        System.out.println("---------------中序遍历");
        tree.inOrder();
        System.out.println("---------------后序遍历");
        tree.postOrder();

    }

    private static class BinaryTree {

        private Node root; // 根

        public void setRoot(Node root) {
            this.root = root;
        }

        /**
         * 前序遍历
         */
        public void preOrder() {
            preOrder(root, 0);
        }

        /**
         * 前序遍历
         *
         * @param node
         * @param depth 层级（用于辅助输出）
         */
        public void preOrder(Node node, int depth) {

            if (node == null) {
                return;
            }

            // 输出当前节点
            this.print(node, depth);

            // 递归左子节点
            if (node.left != null) {
                preOrder(node.left, depth + 1);
            }

            // 递归右子节点
            if (node.right != null) {
                preOrder(node.right, depth + 1);
            }

        }

        /**
         * 中序遍历
         */
        public void inOrder() {
            inOrder(root, 0);
        }

        /**
         * 中序遍历
         *
         * @param node
         * @param depth 层级（用于辅助输出）
         */
        public void inOrder(Node node, int depth) {

            if (node == null) {
                return;
            }

            // 递归左子节点
            if (node.left != null) {
                inOrder(node.left, depth + 1);
            }

            // 输出当前节点
            this.print(node, depth);

            // 递归右子节点
            if (node.right != null) {
                inOrder(node.right, depth + 1);
            }

        }

        /**
         * 后序遍历
         */
        public void postOrder() {
            postOrder(root, 0);
        }

        /**
         * 后序遍历
         *
         * @param node
         * @param depth 层级（用于辅助输出）
         */
        public void postOrder(Node node, int depth) {

            if (node == null) {
                return;
            }

            // 递归左子节点
            if (node.left != null) {
                postOrder(node.left, depth + 1);
            }

            // 递归右子节点
            if (node.right != null) {
                postOrder(node.right, depth + 1);
            }

            // 输出当前节点
            this.print(node, depth);

        }

        /**
         * 按照层级输出节点元素
         *
         * @param node
         * @param depth
         */
        private void print(Node node, int depth) {
            StringBuilder t = new StringBuilder();
            for (int i = 0; i < depth; i++) {
                t.append("\t");
            }
            System.out.printf("%s%s\n", t.toString(), node.element);
        }
    }

    private static class Node {
        private final Object element; // 节点元素
        private Node left; // 左子节点
        private Node right; // 右子节点

        public Node(Object element) {
            this.element = element;
        }
    }
}
