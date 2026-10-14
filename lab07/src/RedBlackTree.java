public class RedBlackTree<T extends Comparable<T>> {

    /* Root of the tree. */
    /* 中文：树的根节点。 */
    RBTreeNode<T> root;

    static class RBTreeNode<T> {

        final T item;
        boolean isBlack;
        RBTreeNode<T> left;
        RBTreeNode<T> right;

        /**
         * Creates a RBTreeNode with item ITEM and color depending on ISBLACK
         * value.
         * 中文：创建一个值为 ITEM 的 RBTreeNode，其颜色由 ISBLACK 决定。
         * @param isBlack 节点是否为黑色；true 表示黑色，false 表示红色
         * @param item 节点存储的元素
         */
        RBTreeNode(boolean isBlack, T item) {
            this(isBlack, item, null, null);
        }

        /**
         * Creates a RBTreeNode with item ITEM, color depending on ISBLACK
         * value, left child LEFT, and right child RIGHT.
         * 中文：创建一个值为 ITEM、颜色由 ISBLACK 决定的 RBTreeNode，
         * 左子节点为 LEFT，右子节点为 RIGHT。
         * @param isBlack 节点是否为黑色；true 表示黑色，false 表示红色
         * @param item 节点存储的元素
         * @param left 左子节点；没有左子节点时为 null
         * @param right 右子节点；没有右子节点时为 null
         */
        RBTreeNode(boolean isBlack, T item, RBTreeNode<T> left,
                   RBTreeNode<T> right) {
            this.isBlack = isBlack;
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Creates an empty RedBlackTree.
     * 中文：创建一棵空的红黑树。
     */
    public RedBlackTree() {
        root = null;
    }

    /**
     * Flips the color of node and its children. Assume that NODE has both left
     * and right children
     * 中文：翻转 node 及其子节点的颜色。可假设 NODE 同时具有左、右子节点。
     * @param node 待翻转颜色的节点；该节点必须同时具有左、右子节点
     */
    void flipColors(RBTreeNode<T> node) {
        // TODO: YOUR CODE HERE
        // 中文：在此处编写你的代码。
        if (node.left == null) {
            return;
        }
        if (node.right == null) {
            return;
        }
        node.isBlack = false;
        node.left.isBlack = true;
        node.right.isBlack = true;
    }

    /**
     * Rotates the given node to the right. Returns the new root node of
     * this subtree. For this implementation, make sure to swap the colors
     * of the new root and the old root!
     * 中文：将给定节点向右旋转，并返回该子树的新根节点。
     * 在此实现中，务必交换新根和旧根的颜色！
     * @param node 待右旋子树的根节点
     * @return 右旋后该子树的新根节点
     */
    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
        // TODO: YOUR CODE HERE
        // 中文：在此处编写你的代码。
        node.left.isBlack = node.isBlack;
        node.isBlack = false;
        RBTreeNode<T> temp = node.left;
        node.left = temp.right;
        temp.right = node;
        return temp;
    }

    /**
     * Rotates the given node to the left. Returns the new root node of
     * this subtree. For this implementation, make sure to swap the colors
     * of the new root and the old root!
     * 中文：将给定节点向左旋转，并返回该子树的新根节点。
     * 在此实现中，务必交换新根和旧根的颜色！
     * @param node 待左旋子树的根节点
     * @return 左旋后该子树的新根节点
     */
    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {
        // TODO: YOUR CODE HERE
        // 中文：在此处编写你的代码。
        node.right.isBlack = node.isBlack;
        node.isBlack = false;
        RBTreeNode<T> temp = node.right;
        node.right = temp.left;
        temp.left = node;
        return temp;
    }

    /**
     * Helper method that returns whether the given node is red. Null nodes (children or leaf
     * nodes) are automatically considered black.
     * 中文：辅助方法，返回给定节点是否为红色。null 节点（子节点或叶子）
     * 自动视为黑色。
     * @param node 待检查的节点，可以为 null
     * @return 当节点非 null 且为红色时返回 true，否则返回 false
     */
    private boolean isRed(RBTreeNode<T> node) {
        return node != null && !node.isBlack;
    }

    /**
     * Inserts the item into the Red Black Tree. Colors the root of the tree black.
     * 中文：将 item 插入红黑树，并将树根设为黑色。
     * @param item 待插入红黑树的元素
     */
    public void insert(T item) {
        root = insert(root, item);
        root.isBlack = true;
    }

    /**
     * Inserts the given node into this Red Black Tree. Comments have been provided to help break
     * down the problem. For each case, consider the scenario needed to perform those operations.
     * Make sure to also review the other methods in this class!
     * 中文：将给定元素插入这棵红黑树。已给注释用于把问题分解为多个步骤；
     * 对每一种操作，请思考在什么条件下需要执行它。也请回顾本类中的其他方法。
     * @param node 当前子树的根节点；空子树为 null
     * @param item 待插入的元素
     * @return 插入元素并完成平衡调整后该子树的根节点
     */
    private RBTreeNode<T> insert(RBTreeNode<T> node, T item) {
        // TODO: Insert (return) new red leaf node.
        // 中文：插入（并返回）新的红色叶节点。
        if (node == null) {
            return new RBTreeNode<>(false, item, null, null);
        }
        // TODO: Handle normal binary search tree insertion.
        // 中文：处理普通二叉搜索树的插入操作。
        if (item.compareTo(node.item) < 0) {
            node.left = insert(node.left, item);
        }
        else if (item.compareTo(node.item) > 0) {
            node.right = insert(node.right, item);
        }
        else {
            return node;
        }
        // TODO: Rotate left operation
        // 中文：执行左旋操作。
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        // TODO: Rotate right operation
        // 中文：执行右旋操作。
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        // TODO: Color flip
        // 中文：执行颜色翻转。
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node; //fix this return statement
        // 中文：修正这条 return 语句。
    }

}
