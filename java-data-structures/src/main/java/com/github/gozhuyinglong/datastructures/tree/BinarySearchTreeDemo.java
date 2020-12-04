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

        System.out.println("----------------------顺序输出");
        tree.orderPrint();
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
            boolean isParentLeft = false;
            if (parentNode.left != null && parentNode.left.compareTo(element) == 0) {
                isParentLeft = true;
            }

            // 删除目标元素
            if (node.left == null && node.right == null) {
                // （1）目标元素为叶子节点，直接删除
                if (isParentLeft) {
                    parentNode.left = null;
                } else {
                    parentNode.right = null;
                }
            } else if (node.left != null && node.right != null) {
                // （2）目标元素即有左子树，也有右子树


            } else {
                // （3）目标元素只有左子树，或只有右子树
                if (isParentLeft) {
                    parentNode.left = node.left != null ? node.left : node.right;
                } else {
                    parentNode.right = node.left != null ? node.left : node.right;
                }
            }
            return true;
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
