package io.github.gozhuyinglong.datastructures.tree;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * B树
 *
 * @author 码农StayUp
 * @date 2020/12/28 0028
 */
public class BTreeDemo {

    public static void main(String[] args) {

        int[] item = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 16, 15, 17, 18, 19, 20, 13};
        BTree tree = new BTree(5);
        for (int i : item) {
            tree.add(new Entry(i, "-->" + i));
        }
        System.out.println("----------------------打印当前树结构");
        System.out.println(JSONObject.toJSONString(tree));
        Entry entry = tree.searchEntry(16);
        System.out.println("查询键16：" + entry.getValue());

        System.out.println("----------------------删除键15");
        tree.remove(15);
        System.out.println(JSONObject.toJSONString(tree));

        System.out.println("----------------------删除键14");
        tree.remove(14);
        System.out.println(JSONObject.toJSONString(tree));

        System.out.println("----------------------删除键17");
        tree.remove(17);
        System.out.println(JSONObject.toJSONString(tree));

        System.out.println("----------------------删除键5");
        tree.remove(5);
        System.out.println(JSONObject.toJSONString(tree));
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

        @Override
        public String toString() {
            return "BTree{" +
                    "root=" + root +
                    '}';
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
                if (node.getChildNodes().size() == 0) {
                    return null;
                }
                return searchEntry(node.getChildNodes().get(-index - 1), key);
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
                if (node.getChildNodes().size() == 0) {
                    return null;
                }
                return searchNode(node.getChildNodes().get(-index - 1), key);
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
            if (node.getChildNodes().size() == 0) {

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
                add(node.getChildNodes().get(-index - 1), entry);
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
            if (node.getChildNodes().size() > 0) {
                List<Node> leftChildNode = node.getChildNodes().subList(0, mid + 1);
                for (Node temp : leftChildNode) {
                    temp.setParentNode(leftNode);
                }
                leftNode.getChildNodes().addAll(leftChildNode);

                List<Node> rightChildNode = node.getChildNodes().subList(mid + 1, node.getEntrys().size() + 1);
                for (Node temp : rightChildNode) {
                    temp.setParentNode(rightNode);
                }
                rightNode.getChildNodes().addAll(rightChildNode);
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
                node.parentNode.getChildNodes().remove(node);
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
            // 先查询出当前元素所在节点
            Node node = searchNode(key);
            if (node == null) {
                return;
            }

            // 删除节点
            int keyIndex = Collections.binarySearch(node.getEntrys(), new Entry(key, null));
            node.getEntrys().remove(keyIndex);
            // 若当前节点的键数小于限定值，删除进行删除后处理
            if (node.getEntrys().size() < min) {
                afterDeletingHandler(node, keyIndex);
            }


        }

        /**
         * 删除后处理：当前节点元素数小于限定值，则根据不同场景选择进行合并、左旋转、右旋转
         *
         * @param node
         */
        private void afterDeletingHandler(Node node, int deletedKeyIndex) {

            // 该节点为内部节点
            if (node.getChildNodes().size() > 0) {
                // 获取删除元素的左右子节点
                Node leftChideNode = node.getChildNodes().get(deletedKeyIndex);
                Node rightChildNode = node.getChildNodes().get(deletedKeyIndex + 1);

                int leftEntrysSize = leftChideNode == null ? 0 : leftChideNode.entrys.size();
                int rightEntrysSize = rightChildNode == null ? 0 : rightChildNode.entrys.size();
                int maxSize = Math.max(leftEntrysSize, rightEntrysSize);

                // 先向左右子节点借
                if (maxSize <= min) {
                    // 若左右子节点达到限定值，则合并左右子节点
                    merge(leftChideNode, rightChildNode);
                    return;
                }
                // 向左子节点借
                Entry entry;
                if (maxSize == leftEntrysSize) {
                    entry = leftChideNode.getEntrys().get(leftChideNode.getEntrys().size() - 1);
                    leftChideNode.getEntrys().remove(entry);
                } else {
                    // 向右子节点借
                    entry = rightChildNode.getEntrys().get(0);
                    rightChildNode.getEntrys().remove(entry);
                }
                node.add(entry);
                return;
            }

            // 该节点为叶子节点
            Node parentNode = node.parentNode;
            // 当前节点在父节点中的下标值
            int nodeIndex = parentNode.getChildNodes().indexOf(node);
            // 左兄弟节点
            Node leftNode = nodeIndex > 0 ? parentNode.getChildNodes().get(nodeIndex - 1) : null;
            // 右兄弟节点
            Node rightNode = nodeIndex == parentNode.getChildNodes().size() - 1 ? null : parentNode.getChildNodes().get(nodeIndex + 1);

            int leftEntrysSize = leftNode == null ? 0 : leftNode.entrys.size();
            int rightEntrysSize = rightNode == null ? 0 : rightNode.entrys.size();
            int maxSize = Math.max(leftEntrysSize, rightEntrysSize);

            // 左右兄弟节点元素数均达到限定值，合并
            if (maxSize <= min) {
                if (leftNode != null) {
                    // 与左兄弟节点合并
                    merge(node, leftNode, nodeIndex - 1);
                } else if (rightNode != null) {
                    // 与右兄弟节点合并
                    merge(node, rightNode, nodeIndex);
                }
                return;
            }

            if (maxSize == leftEntrysSize) {
                // 向左节点借--> 右旋转
                rightRotate(node, nodeIndex, leftNode);
            } else {
                // 向右节点借--> 左旋转
                leftRotate(node, nodeIndex, rightNode);
            }
        }

        /**
         * 将当前节点与兄弟节点、中间值合并
         *
         * @param node             当前节点
         * @param brotherNode      兄弟节点
         * @param parentEntryIndex 中间值
         */
        private void merge(Node node, Node brotherNode, int parentEntryIndex) {
            // 创建新的节点
            Node parentNode = node.getParentNode();
            Node newNode = new Node();
            newNode.getEntrys().addAll(node.getEntrys());
            newNode.getEntrys().addAll(brotherNode.getEntrys());
            newNode.add(parentNode.getEntrys().get(parentEntryIndex));
            // 删除原节点及关系
            parentNode.getEntrys().remove(parentEntryIndex);
            parentNode.getChildNodes().remove(node);
            parentNode.getChildNodes().remove(brotherNode);
            if (node.getChildNodes().size() > 0) {
                for (Node childNode : node.getChildNodes()) {
                    newNode.addChild(childNode);
                    childNode.setParentNode(newNode);
                }
            }
            if (brotherNode.getChildNodes().size() > 0) {
                for (Node childNode : brotherNode.getChildNodes()) {
                    newNode.addChild(childNode);
                    childNode.setParentNode(newNode);
                }
            }
            // 若原节点为根节点，则当前节点为新的根节点
            if (parentNode.getEntrys().size() == 0) {
                root = newNode;
                return;
            } else {
                parentNode.addChild(newNode);
                newNode.setParentNode(parentNode);
            }


            // 合并后，若父节点的元素小于限定值，则再次合并（原则上是与最少元素数节点合并）
            if (parentNode.getEntrys().size() < min) {
                Node grandfatherNode = parentNode.getParentNode();
                if (grandfatherNode == null) {
                    return;
                }
                int nodeIndex = Collections.binarySearch(grandfatherNode.getChildNodes(), parentNode);
                // 左兄弟节点
                Node leftNode = nodeIndex > 0 ? grandfatherNode.getChildNodes().get(nodeIndex - 1) : null;
                // 右兄弟节点
                Node rightNode = nodeIndex >= grandfatherNode.getChildNodes().size() - 1 ? null : grandfatherNode.getChildNodes().get(nodeIndex + 1);
                int leftEntrysSize = leftNode == null ? 0 : leftNode.entrys.size();
                int rightEntrysSize = rightNode == null ? 0 : rightNode.entrys.size();
                int minSize = Math.min(leftEntrysSize, rightEntrysSize);
                if (minSize > 0) {
                    if (leftEntrysSize == minSize) {
                        merge(parentNode, leftNode, nodeIndex - 1);
                    } else {
                        merge(parentNode, rightNode, nodeIndex);
                    }
                } else if (leftEntrysSize > 0) {
                    merge(parentNode, leftNode, nodeIndex - 1);
                } else if (rightEntrysSize > 0) {
                    merge(parentNode, rightNode, nodeIndex);
                }
            }

        }

        /**
         * 合并两个兄弟节点
         *
         * @param node        当前节点
         * @param brotherNode 兄弟节点
         */
        private void merge(Node node, Node brotherNode) {
            Node parentNode = node.getParentNode();
            Node newNode = new Node();
            newNode.getEntrys().addAll(node.getEntrys());
            newNode.getEntrys().addAll(brotherNode.getEntrys());
            Collections.sort(newNode.getEntrys());

            newNode.setParentNode(parentNode);
            parentNode.getChildNodes().remove(node);
            parentNode.getChildNodes().remove(brotherNode);
            parentNode.addChild(newNode);
        }

        /**
         * 左旋转
         * （1）将父节点的中间值元素删除，并添加到当前节点中
         * （2）将右兄弟节点中最小元素删除，并添加到父节点中
         *
         * @param node      当前节点
         * @param nodeIndex 中间值索引
         * @param rightNode 右节点
         */
        private void leftRotate(Node node, int nodeIndex, Node rightNode) {
            Entry parentEntry = node.getParentNode().getEntrys().get(nodeIndex);
            node.add(parentEntry);
            node.getParentNode().getEntrys().remove(parentEntry);
            Entry rightEntry = rightNode.getEntrys().get(0);
            node.getParentNode().add(rightEntry);
            rightNode.getEntrys().remove(rightEntry);
        }

        /**
         * 右旋转
         * （1）将父节点的中间值元素删除，并添加到当前节点中
         * （2）将左兄弟节点中最大元素删除，并添加到父节点中
         *
         * @param node      当前节点
         * @param nodeIndex 中间值索引
         * @param leftNode  左节点
         */
        private void rightRotate(Node node, int nodeIndex, Node leftNode) {
            Entry parentEntry = node.getParentNode().getEntrys().get(nodeIndex - 1);
            node.add(parentEntry);
            node.getParentNode().getEntrys().remove(parentEntry);
            Entry leftEntry = leftNode.getEntrys().get(leftNode.getEntrys().size() - 1);
            node.getParentNode().add(leftEntry);
            leftNode.getEntrys().remove(leftEntry);
        }
    }

    /**
     * B树的节点类
     */
    private static class Node implements Comparable<Node> {

        private final List<Entry> entrys; // 当前节点的键值对
        private final List<Node> childNodes; // 子节点，使用数组存储子节点
        private Node parentNode; // 父节点

        public Node() {
            entrys = new ArrayList<>();
            childNodes = new ArrayList<>();
        }

        public List<Entry> getEntrys() {
            return entrys;
        }

        public List<Node> getChildNodes() {
            return childNodes;
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
            childNodes.add(node);
            Collections.sort(childNodes);
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

        @Override
        public String toString() {
            return "Node{" +
                    "entrys=" + entrys +
                    ", childNodes=" + childNodes +
                    '}';
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

        @Override
        public String toString() {
            return "{key = " + key + "}";
        }
    }
}
