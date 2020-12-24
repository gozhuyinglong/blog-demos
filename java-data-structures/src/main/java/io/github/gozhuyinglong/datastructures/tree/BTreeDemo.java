package io.github.gozhuyinglong.datastructures.tree;

import java.util.Map;

/**
 * B树
 *
 * @author ZhuYinglong
 * @date 2020/12/22 0022
 */
public class BTreeDemo {


    public static void main(String[] args) {


    }

    private static class BTree {

        private Node root;

        public Node getRoot() {
            return root;
        }

        /**
         * 添加一个键
         *
         * @param entry
         */
        public void add(Entry<Integer, String> entry) {
            if (entry == null) {
                return;
            }

            if (root == null) {
                Node node = new Node();
                node.add(entry);
                return;
            }
            add(entry, root);
        }

        /**
         * 添加一个键，递归插入
         *
         * @param entry
         * @param node
         */
        private void add(Entry<Integer, String> entry, Node node) {

            // 当前节点为叶子节点，开始插入
            if (node.getChildSize() == 0) {

                // 当前节点已满，分裂该节点
                if (node.entrys.length == node.getSize()) {
                    // 未达到最大分支，则直接分裂
                    // 若已达到最大分支，则...
                    return;
                }

                // 当前节点未满，则直接插入
                node.add(entry);
                return;
            }

            // 当前节点为内部节点，继续往下调用（新插入的节点，只能是叶子节点）
            for (int i = 0; i < node.entrys.length; i++) {
                if (node.entrys[i] == null || node.entrys[i].key > entry.key) {
                    add(entry, node.getChildNode()[i]);
                    break;
                }

                if (node.entrys.length == i + 1) {
                    add(entry, node.getChildNode()[i + 1]);
                }
            }

        }

        /**
         * 使用顺序查找法，找到应该插入的位置
         *
         * @param entrys
         * @param target
         * @return
         */
        private int sequenceSearchIndex(Entry<Integer, String>[] entrys, Entry<Integer, String> target) {
            int i = 0;
            for (; i < entrys.length; i++) {
                // 添加到数组尾部
                if (entrys[i] == null || entrys[i].key > target.key) {
                    break;
                }
            }
            return i;
        }
    }

    private static class Node {

        public static final int M = 3; // B树的阶

        private final Entry<Integer, String>[] entrys; // 当前节点的键值对
        private Node parentNode; // 父节点
        private Node[] childNode; // 子节点，使用数组存储子节点

        public Node getParentNode() {
            return parentNode;
        }

        public void setParentNode(Node parentNode) {
            this.parentNode = parentNode;
        }

        public Node[] getChildNode() {
            return childNode;
        }

        public Node() {
            entrys = new Entry[M - 1];
            childNode = new Node[M];
        }

        /**
         * 获取当前节点键的大小
         *
         * @return
         */
        public int getSize() {
            int i = 0;
            for (; i < entrys.length; i++) {
                if (entrys[i] == null) {
                    break;
                }
            }
            return i;
        }

        /**
         * 添加一个键，并保持顺序
         *
         * @param entry
         */
        public void add(Entry<Integer, String> entry) {
            if (entry == null) {
                return;
            }
            for (int i = 0; i < entrys.length; i++) {
                // 添加到数组尾部
                if (entrys[i] == null) {
                    entrys[i] = entry;
                    break;
                }
                // 添加到数组中间
                if (entrys[i].key > entry.key) {
                    System.arraycopy(entrys, i, entrys, i + 1, M - i - 1);
                    entrys[i] = entry;
                    break;
                }
            }
        }

        /**
         * 获取子节点数量
         *
         * @return
         */
        public int getChildSize() {
            int i = 0;
            for (; i < childNode.length; i++) {
                if (childNode[i] == null) {
                    break;
                }
            }
            return i;
        }

        /**
         * 添加一个子节点，并保持顺序
         *
         * @param node
         */
        public void addChild(Node node) {
            if (node == null) {
                return;
            }
            for (int i = 0; i < entrys.length; i++) {
                // 添加到数组尾部
                if (childNode[i] == null) {
                    childNode[i] = node;
                    break;
                }
                // 添加到数组中间
                if (childNode[i].entrys[0].key > node.entrys[0].key) {
                    System.arraycopy(childNode, i, childNode, i + 1, M - i - 1);
                    childNode[i] = node;
                    break;
                }
            }
        }


    }

    /**
     * 定位一个键值对，其实现了 Map.Entry<K, V> 接口
     *
     * @param <K>
     * @param <V>
     */
    private static class Entry<K, V> implements Map.Entry<K, V> {

        final K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            V oldValue = this.value;
            this.value = newValue;
            return oldValue;
        }
    }
}
