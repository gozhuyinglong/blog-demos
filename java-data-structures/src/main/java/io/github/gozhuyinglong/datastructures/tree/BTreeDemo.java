package io.github.gozhuyinglong.datastructures.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * B树
 *
 * @author ZhuYinglong
 * @date 2020/12/28 0028
 */
public class BTreeDemo {

    public static void main(String[] args) {

        int[] item = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
        BTree tree = new BTree(5);
        for (int i : item) {
            tree.add(new Entry(i, "-->" + i));
        }
        Entry entry = tree.searchEntry(16);
        System.out.println(entry.getValue());
    }

    /**
     * B树类
     */
    private static class BTree {

        private final int m; // B树的阶
        private final int min;// 元素最小值
        private Node root; // 树根

        public BTree(int m) {
            this.m = m;
            this.min = (int) Math.ceil(m / 2.0) - 1;
        }

        public Node getRoot() {
            return root;
        }

        /**
         * 根据键搜索数据记录
         *
         * @param key
         * @return
         */
        public Entry searchEntry(int key) {
            return searchEntry(root, key);
        }

        /**
         * 根据键搜索数据记录 - 递归
         *
         * @param node
         * @param key
         * @return
         */
        private Entry searchEntry(Node node, int key) {
            if (node == null) {
                return null;
            }
            // 使用二分查找法定位下标
            int index = Collections.binarySearch(node.getEntrys(), new Entry(key, null));
            if (index >= 0) {
                return node.getEntrys().get(index);
            } else {
                if (node.getChildNode().size() == 0) {
                    return null;
                }
                return searchEntry(node.getChildNode().get(-index - 1), key);
            }
        }

        /**
         * 根据键搜索记录所在节点
         *
         * @param key
         * @return
         */
        public Node searchNode(int key) {
            return searchNode(root, key);
        }

        /**
         * 根据键搜索记录所在节点 - 递归
         *
         * @param node
         * @param key
         * @return
         */
        private Node searchNode(Node node, int key) {
            if (node == null) {
                return null;
            }
            // 使用二分查找法定位下标
            int index = Collections.binarySearch(node.getEntrys(), new Entry(key, null));
            if (index >= 0) {
                return node;
            } else {
                if (node.getChildNode().size() == 0) {
                    return null;
                }
                return searchNode(node.getChildNode().get(-index - 1), key);
            }
        }

        /**
         * 添加元素
         *
         * @param entry
         */
        public void add(Entry entry) {
            // root为空，直接创建
            if (root == null) {
                Node node = new Node();
                node.add(entry);
                root = node;
                return;
            }
            add(root, entry);
        }

        /**
         * 添加元素 - 递归
         *
         * @param node
         * @param entry
         */
        private void add(Node node, Entry entry) {

            // 当前节点为叶子节点
            if (node.getChildNode().size() == 0) {

                // 如果当前节点元素未满，直接添加元素
                if (node.getEntrys().size() < m - 1) {
                    node.add(entry);
                    return;
                }
                // 如果当前节点元素已满，则分裂当前节点
                node.getEntrys().add(entry);
                split(node);
                return;
            }

            // 当前节点为内部节点，继续往下调用（新插入的节点，只能是叶子节点）
            // 使用二分查找法找到要插入的分支
            int index = Collections.binarySearch(node.getEntrys(), entry);
            if (index < 0) {
                add(node.getChildNode().get(-index - 1), entry);
            }
        }

        /**
         * 分离当前节点
         *
         * @param node
         */
        private void split(Node node) {
            int mid = node.getEntrys().size() / 2;
            // 分隔值
            Entry separateEntry = node.getEntrys().get(mid);
            // 分离后的左节点
            Node leftNode = new Node();
            leftNode.getEntrys().addAll(node.getEntrys().subList(0, mid));
            // 分离后的右节点
            Node rightNode = new Node();
            rightNode.getEntrys().addAll(node.getEntrys().subList(mid + 1, node.getEntrys().size()));
            // 分离子节点
            if (node.getChildNode().size() > 0) {
                List<Node> leftChildNode = node.getChildNode().subList(0, mid + 1);
                for (Node temp : leftChildNode) {
                    temp.setParentNode(leftNode);
                }
                leftNode.getChildNode().addAll(leftChildNode);

                List<Node> rightChildNode = node.getChildNode().subList(mid + 1, node.getEntrys().size() + 1);
                for (Node temp : rightChildNode) {
                    temp.setParentNode(rightNode);
                }
                rightNode.getChildNode().addAll(rightChildNode);
            }

            // 当前节点为根节点
            if (node.parentNode == null) {
                Node newRoot = new Node();
                newRoot.add(separateEntry);
                root = newRoot;
                leftNode.parentNode = root;
                rightNode.parentNode = root;
                root.addChild(leftNode).addChild(rightNode);
            } else {
                node.parentNode.add(separateEntry);
                leftNode.parentNode = node.parentNode;
                rightNode.parentNode = node.parentNode;
                node.parentNode.addChild(leftNode).addChild(rightNode);
                node.parentNode.getChildNode().remove(node);
                // 若其父节点溢出，继续分裂
                if (node.parentNode.getEntrys().size() > m - 1) {
                    split(node.parentNode);
                }
            }
        }

        /**
         * 删除元素
         *
         * @param key
         */
        public void remove(int key) {
            Node node = searchNode(key);
            if (node == null) {
                return;
            }

            if (node.childNode.size() > 0) {
                // 删除的是内部节点

            } else {
                // 删除的是叶子节点
                int index = Collections.binarySearch(node.getEntrys(), new Entry(key, null));
                node.getEntrys().remove(index);
                if (node.parentNode == null) {
                    return;
                }

                // 键数小于最小值，向兄弟节点借
                if (node.getEntrys().size() < min) {
                    Node parentNode = node.parentNode;
                    int i = parentNode.getChildNode().indexOf(parentNode);
                    if (i == 0) {
                        if(parentNode.getChildNode().get(1).getEntrys().size() == min) {
                            // 如果不够借，则合并
                        } else {
                            // 够借则选装
                        }
                    }
                }
            }

        }
    }

    /**
     * B树的节点类
     */
    private static class Node implements Comparable<Node> {

        private final List<Entry> entrys; // 当前节点的键值对
        private final List<Node> childNode; // 子节点，使用数组存储子节点
        private Node parentNode; // 父节点

        public Node() {
            entrys = new ArrayList<>();
            childNode = new ArrayList<>();
        }

        public List<Entry> getEntrys() {
            return entrys;
        }

        public List<Node> getChildNode() {
            return childNode;
        }

        public void setParentNode(Node parentNode) {
            this.parentNode = parentNode;
        }

        public Node getParentNode() {
            return parentNode;
        }

        public Node add(Entry entry) {
            entrys.add(entry);
            Collections.sort(entrys);
            return this;
        }

        public Node addChild(Node node) {
            childNode.add(node);
            Collections.sort(childNode);
            return this;
        }

        /**
         * 按照键值中第一个key进行排序
         *
         * @param o
         * @return
         */
        @Override
        public int compareTo(Node o) {
            return Integer.compare(entrys.get(0).getKey(), o.getEntrys().get(0).getKey());
        }
    }


    /**
     * 定位一个键值对，其实现了 Map.Entry<K, V> 接口
     */
    private static class Entry implements Comparable<Entry> {

        final int key;
        String value;

        public Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public String setValue(String newValue) {
            String oldValue = this.value;
            this.value = newValue;
            return oldValue;
        }

        /**
         * 按照key进行排序
         *
         * @param o
         * @return
         */
        @Override
        public int compareTo(Entry o) {
            return Integer.compare(key, o.getKey());
        }
    }
}
