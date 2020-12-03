package com.github.gozhuyinglong.datastructures.tree;

/**
 * 二叉查找树（二叉排序树）
 *
 * @author ZhuYinglong
 * @date 2020/12/3 0003
 */
public class BinarySearchTreeDemo {

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();


        System.out.println("----------------------添加元素");
        System.out.println(tree.add(5));
        System.out.println(tree.add(4));
        System.out.println(tree.add(6));
        System.out.println(tree.add(8));
        System.out.println(tree.add(3));
        System.out.println(tree.add(1));
        System.out.println(tree.add(2));
        System.out.println(tree.add(9));
        System.out.println(tree.add(7));

        System.out.println("----------------------顺序输出");
        tree.orderPrint();

        System.out.println("----------------------查找元素");
        System.out.println(tree.find(7));

        System.out.println("----------------------查找最小值");
        System.out.println(tree.findMin());

        System.out.println("----------------------查找最大值");
        System.out.println(tree.findMax());

        System.out.println("----------------------是否包含某值");
        System.out.println("是否包含[10]\t" + tree.contains(10));
        System.out.println("是否包含[2] \t" + tree.contains(2));

        System.out.println("----------------------删除某个值");
        System.out.println("删除[10]\t" + tree.remove(10));
        System.out.println("删除[2] \t" + tree.remove(2));
    }

    private static class BinarySearchTree {
        private Node root; // 树根

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
         * 删除某元素
         *
         * @param element
         * @return
         */
        public boolean remove(int element) {
            if (root == null) {
                return false;
            }
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
         * 删除某元素（递归）
         *
         * @param node
         * @param element
         * @return
         */
        private boolean remove(Node parentNode, Node node, int element) {
            if (node == null) {
                return false;
            }

            int compareResult = node.compareTo(element);
            if (compareResult > 0) {
                return remove(node, node.right, element);
            }
            if (compareResult < 0) {
                return remove(node, node.left, element);
            }
            // 当前节点为root节点
            if (parentNode == null) {
                if (root.right == null) {
                    root = root.left;
                } else {
                    root.right.left = root.left;
                    root = root.right;
                }
                return true;
            }

            if (parentNode.right.compareTo(node.element) == 0) {
                // 当前节点是父节点的右子树

            } else {
                // 当前节点是父节点的左子树
            }



            return false;

        }
    }

    private static class Node implements Comparable<Integer> {
        private final Integer element; // 数据元素
        private Node left; // 左子树
        private Node right; // 右子树

        private Node(Integer element) {
            this.element = element;
        }

        @Override
        public int compareTo(Integer o) {
            if (o < element) {
                return -1;
            } else if (o > element) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    '}';
        }
    }
}
