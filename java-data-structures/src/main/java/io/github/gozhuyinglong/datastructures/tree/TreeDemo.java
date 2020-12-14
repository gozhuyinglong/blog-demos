package io.github.gozhuyinglong.datastructures.tree;

/**
 * 树
 *
 * @author 码农StayUp
 * @date 2020/11/30 0030
 */
public class TreeDemo {

    public static void main(String[] args) {
        new Tree().initTree().listAll();

    }

    private static class Tree {

        private TreeNode root; // 树根

        /**
         * 初始化一棵树
         */
        private Tree initTree() {

            TreeNode a = new TreeNode("A");
            TreeNode b = new TreeNode("B");
            TreeNode c = new TreeNode("C");
            TreeNode d = new TreeNode("D");
            TreeNode e = new TreeNode("E");
            TreeNode f = new TreeNode("F");
            TreeNode g = new TreeNode("G");
            TreeNode h = new TreeNode("H");
            TreeNode i = new TreeNode("I");
            TreeNode j = new TreeNode("J");
            TreeNode k = new TreeNode("K");
            TreeNode l = new TreeNode("L");
            TreeNode m = new TreeNode("M");

            root = a;

            a.firstChild = b;

            b.nextSibling = c;

            c.nextSibling = d;
            c.firstChild = f;

            d.nextSibling = e;
            d.firstChild = g;

            e.firstChild = i;

            g.nextSibling = h;

            h.firstChild = l;

            i.nextSibling = j;

            j.nextSibling = k;

            l.nextSibling = m;

            return this;
        }


        /**
         * 遍历一棵树，从root开始
         */
        public void listAll() {
            listAll(root, 0);
        }

        /**
         * 遍历一棵树
         *
         * @param node  树节点
         * @param depth 层级（用于辅助输出）
         */
        public void listAll(TreeNode node, int depth) {
            StringBuilder t = new StringBuilder();
            for (int i = 0; i < depth; i++) {
                t.append("\t");
            }
            System.out.printf("%s%s\n", t.toString(), node.element);

            // 先遍历子节点，子节点的层级需要+1
            if (node.firstChild != null) {
                listAll(node.firstChild, depth + 1);
            }

            // 后遍历兄弟节点，兄弟节点的层级不变
            if (node.nextSibling != null) {
                listAll(node.nextSibling, depth);
            }
        }


    }

    private static class TreeNode {
        private final Object element; // 当前节点数据
        private TreeNode firstChild; // 当前节点的第一个子节点
        private TreeNode nextSibling; // 当前节点的下一个兄弟节点

        public TreeNode(Object element) {
            this.element = element;
        }

    }
}
