package io.github.gozhuyinglong.datastructures.tree;


/**
 * AVL树（平衡二叉树）
 *
 * @author 码农StayUp
 * @date 2020/12/14 0014
 */
public class AVLTreeDemo {

    public static void main(String[] args) {
        System.out.println("----------------------添加元素");
        int[] array = {8, 5, 9, 3, 6, 7};
        AVLTree tree = new AVLTree();
        for (int element : array) {
            System.out.printf("添加元素[%s]\n", element);
            tree.add(element);
        }

        System.out.println("----------------------顺序输出（中序遍历）");
        tree.orderPrint();

        System.out.println("----------------------当前树的高度");
        System.out.println("当前树：" + tree.getRoot().height());
        System.out.println("左子树：" + tree.getRoot().leftHeight());
        System.out.println("右子树：" + tree.getRoot().rightHeight());

        System.out.println("----------------------删除元素");
        tree.remove(3);
        System.out.println("----------------------当前树的高度");
        System.out.println("当前树：" + tree.getRoot().height());
        System.out.println("左子树：" + tree.getRoot().leftHeight());
        System.out.println("右子树：" + tree.getRoot().rightHeight());

        System.out.println("----------------------删除元素");
        tree.remove(5);
        System.out.println("----------------------当前树的高度");
        System.out.println("当前树：" + tree.getRoot().height());
        System.out.println("左子树：" + tree.getRoot().leftHeight());
        System.out.println("右子树：" + tree.getRoot().rightHeight());
    }

    private static class AVLTree {
        private Node root; // 树根

        public Node getRoot() {
            return root;
        }

        /**
         * 添加元素
         *
         * @param element
         * @return
         */
        public void add(int element) {
            if (root == null) {
                root = new Node(element);
                return;
            }
            add(root, element);
        }

        /**
         * 添加元素（递归）
         *
         * @param node
         * @param element
         * @return
         */
        private void add(Node node, int element) {
            if (node.compareTo(element) < 0) {
                if (node.left == null) {
                    node.left = new Node(element);
                } else {
                    add(node.left, element);
                }
            } else if (node.compareTo(element) > 0) {
                if (node.right == null) {
                    node.right = new Node(element);
                } else {
                    add(node.right, element);
                }
            }
            balance(node);
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
        public void remove(int element) {
            if (root == null) {
                return;
            }
            // 如果删除的元素是root
            if (root.compareTo(element) == 0) {
                if (root.right == null) {
                    root = root.left;
                } else {
                    root.right.left = root.left;
                    root = root.right;
                }
                balance(root);
                return;
            }
            remove(null, root, element);
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
        private void remove(Node parentNode, Node node, int element) {
            if (node == null) {
                return;
            }
            // 先找到目标元素
            int compareResult = node.compareTo(element);
            if (compareResult < 0) {
                remove(node, node.left, element);
            } else if (compareResult > 0) {
                remove(node, node.right, element);
            } else {
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
            }
            balance(node);
        }

        /**
         * 通过旋转对做进行平衡
         *
         * @param node
         */
        public void balance(Node node) {

            if (node == null) {
                return;
            }

            if (node.leftHeight() - node.rightHeight() > 1) {
                if (node.left.rightHeight() > node.left.leftHeight()) {
                    node.left.leftRotate();
                }
                node.rightRotate();

            } else if (node.rightHeight() - node.leftHeight() > 1) {
                if (node.right.leftHeight() > node.right.rightHeight()) {
                    node.right.rightHeight();
                }
                node.leftRotate();
            }

        }
    }

    private static class Node implements Comparable<Integer> {
        private int element; // 数据元素
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

        /**
         * 当前节点的左子树高度
         *
         * @return
         */
        public int leftHeight() {
            if (left == null) {
                return 0;
            }
            return left.height();
        }

        /**
         * 当前节点的右子树高度
         *
         * @return
         */
        public int rightHeight() {
            if (right == null) {
                return 0;
            }
            return right.height();
        }

        /**
         * 左旋转（向左旋转）
         * <p>
         * 以当前节点为树根，当（右子树的高度 - 左子树的高度） > 1时，进行左旋转：
         * （1）将当前（根）节点向左下移，成为新的左子节点；并将其右子节点设为原根节点右子树的左子树
         * （2）将当前节点的右节点上移，成新的树根（当前节点）；并将左子节点指向新的左子节点（原树根）
         */
        public void leftRotate() {
            // 将当前节点向左下移，成为新的左节点
            Node newLeftNode = new Node(element);
            newLeftNode.left = left;
            // 将右子节点设为原根节点右子树的左子树
            newLeftNode.right = right.left;

            // 将右节点上移，成为新的树根（当前节点）
            element = right.element;
            // 将左子节点设为新的左子节点（原树根）
            left = newLeftNode;
            right = right.right;

        }

        /**
         * 右旋转（向右旋转）
         * <p>
         * 以当前节点为树根，当（左子树的高度 - 右子树的高度） > 1时，进行右旋转：
         * （1）将当前（根）节点向右下移，成为新的右子节点；并将其左子节点设为原根节点左子树的右子树
         * （2）将当前节点的右节点上移，成为新的树根（当前节点）；并将右子节点指向新的右子节点（原树根）
         */
        public void rightRotate() {
            // 将当前节点向右下移，成为新的右子节点
            Node newRightNode = new Node(element);
            // 将左子节点指向原根节点的左子树的右子树
            newRightNode.left = left.right;
            newRightNode.right = right;

            // 将左子节点上移，成为新的树根（当前节点）
            element = left.element;
            left = left.left;
            // 将右子节点设为新的右子节点（原树根）
            right = newRightNode;
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
