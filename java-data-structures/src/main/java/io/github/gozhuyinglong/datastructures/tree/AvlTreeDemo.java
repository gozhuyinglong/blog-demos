package io.github.gozhuyinglong.datastructures.tree;


/**
 * AVL树（平衡二叉树）
 *
 * @author ZhuYinglong
 * @date 2020/12/14 0014
 */
public class AvlTreeDemo {

    public static void main(String[] args) {
        System.out.println("----------------------添加元素");
        int[] array = {3, 2, 1};
        AvlTree tree = new AvlTree();
        for (int element : array) {
            System.out.printf("添加元素[%s] --> %s\n", element, tree.add(element));
        }

        System.out.println("----------------------顺序输出（中序遍历）");
        tree.orderPrint();
        System.out.println("----------------------当前树的高度");
        System.out.println(tree.height());

    }

    private static class AvlTree {
        private Node root; // 树根

        /**
         * 获取当前树的高度
         *
         * @return
         */
        public int height() {
            return root.height();
        }

        /**
         * 添加元素
         *
         * @param element
         * @return
         */
        public boolean add(int element) {
            if (root == null) {
                root = new Node(element);
                return true;
            }
            return add(root, element);
        }

        /**
         * 添加元素（递归）
         *
         * @param node
         * @param element
         * @return
         */
        private boolean add(Node node, int element) {
            if (node.compareTo(element) < 0) {
                if (node.left == null) {
                    node.left = new Node(element);
                    return true;
                } else {
                    return add(node.left, element);
                }
            } else if (node.compareTo(element) > 0) {
                if (node.right == null) {
                    node.right = new Node(element);
                    return true;
                } else {
                    return add(node.right, element);
                }
            } else {
                return false;
            }
        }

        /**
         * 查询元素
         *
         * @param element
         * @return
         */
        public Node find(int element) {
            if (root == null) {
                return null;
            }
            return find(root, element);
        }

        /**
         * 查询元素（递归）
         *
         * @param node
         * @param element
         * @return
         */
        private Node find(Node node, int element) {
            if (node == null) {
                return null;
            }
            int compareResult = node.compareTo(element);
            if (compareResult < 0) {
                return find(node.left, element);
            } else if (compareResult > 0) {
                return find(node.right, element);
            } else {
                return node;
            }
        }

        /**
         * 查找最大值
         *
         * @return
         */
        public Node findMax() {
            return findMax(root);
        }

        /**
         * 查找最大值（递归）
         *
         * @param node
         * @return
         */
        private Node findMax(Node node) {
            if (node.right == null) {
                return node;
            }
            return findMax(node.right);
        }

        /**
         * 查找最小值
         *
         * @return
         */
        private Node findMin() {
            return findMin(root);
        }

        /**
         * 查找最小值（递归）
         *
         * @param node
         * @return
         */
        private Node findMin(Node node) {
            if (node.left == null) {
                return node;
            }
            return findMin(node.left);
        }

        /**
         * 顺序输出
         */
        public void orderPrint() {
            orderPrint(root);
        }


        /**
         * 顺序输出（递归）
         *
         * @param node
         */
        private void orderPrint(Node node) {

            if (node == null) {
                return;
            }

            // 递归左子节点
            if (node.left != null) {
                orderPrint(node.left);
            }

            // 输出当前节点
            System.out.println(node.element);

            // 递归右子节点
            if (node.right != null) {
                orderPrint(node.right);
            }

        }

        /**
         * 是否包含某值
         *
         * @param element
         * @return
         */
        public boolean contains(int element) {
            if (find(element) == null) {
                return false;
            }
            return true;
        }

        /**
         * 删除目标元素
         *
         * @param element
         * @return
         */
        public boolean remove(int element) {
            if (root == null) {
                return false;
            }
            // 如果删除的元素是root
            if (root.compareTo(element) == 0) {
                if (root.right == null) {
                    root = root.left;
                } else {
                    root.right.left = root.left;
                    root = root.right;
                }
                return true;
            }
            return remove(null, root, element);
        }

        /**
         * 删除目标元素（递归），有三种情况：
         * （1）目标元素为叶子节点
         * （2）目标元素即有左子树，也有右子树
         * （3）目标元素只有左子树，或只有右子树
         *
         * @param parentNode 当前节点的父节点
         * @param node       当前节点（若当前节点上的元素与要删除的元素匹配，则删除当前节点）
         * @param element    要删除的元素
         * @return
         */
        private boolean remove(Node parentNode, Node node, int element) {
            if (node == null) {
                return false;
            }
            // 先找到目标元素
            int compareResult = node.compareTo(element);
            if (compareResult < 0) {
                return remove(node, node.left, element);
            }
            if (compareResult > 0) {
                return remove(node, node.right, element);
            }

            // 找到目标元素，判断该节点是父节点的左子树还是右子树
            boolean isLeftOfParent = false;
            if (parentNode.left != null && parentNode.left.compareTo(element) == 0) {
                isLeftOfParent = true;
            }

            // 删除目标元素
            if (node.left == null && node.right == null) { // （1）目标元素为叶子节点，直接删除
                if (isLeftOfParent) {
                    parentNode.left = null;
                } else {
                    parentNode.right = null;
                }
            } else if (node.left != null && node.right != null) { // （2）目标元素即有左子树，也有右子树
                // 找到右子树最小值（叶子节点），并将其删除
                Node minNode = findMin(node.right);
                remove(minNode.element);
                // 将该最小值替换要删除的目标节点
                minNode.left = node.left;
                minNode.right = node.right;
                if (isLeftOfParent) {
                    parentNode.left = minNode;
                } else {
                    parentNode.right = minNode;
                }

            } else { // （3）目标元素只有左子树，或只有右子树
                if (isLeftOfParent) {
                    parentNode.left = node.left != null ? node.left : node.right;
                } else {
                    parentNode.right = node.left != null ? node.left : node.right;
                }
            }
            return true;
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
         * 获取节点的高度，如果是空则高度为0
         *
         * @param node
         * @return
         */
//        private int height(Node node) {
//            return node == null ? 0 : node.height;
//        }

        /**
         * 左旋转
         *
         * @param k1 原树根
         * @return 新树根
         */
//        private Node leftRotate(Node k1) {
//            Node k2 = k1.right;
//            k1.right = k2.left;
//            k2.left = k1;
//            k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
//            k2.height = Math.max(k1.height, height(k2.right)) + 1;
//            return k2;
//        }

        /**
         * 右旋转
         *
         * @param k2 原树根
         * @return 新树根
         */
//        private Node rightRotate(Node k2) {
//            Node k1 = k2.left;
//            k2.left = k1.right;
//            k1.right = k2;
//            k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
//            k1.height = Math.max(height(k1.left), k2.height) + 1;
//            return k1;
//        }
    }

    private static class Node implements Comparable<Integer> {
        private final int element; // 数据元素
        private Node left; // 左子节点
        private Node right; // 右子节点

        public Node(int element) {
            this.element = element;
        }

        /**
         * 以当前节点为根的高度
         *
         * @return
         */
        public int height() {
            return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
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
