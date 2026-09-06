public class UnionFind {
    // TODO: Instance variables
    // 中文：实例变量。

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    /* 中文：创建一个包含 N 个元素的并查集数据结构。初始时，
       所有元素都分别属于互不相交的集合。 */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        // 中文：在此处编写你的代码。
    }

    /* Returns the size of the set V belongs to. */
    /* 中文：返回 V 所属集合的大小。 */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        // 中文：在此处编写你的代码。
        return -1;
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    /* 中文：返回 V 的父节点。如果 V 是某棵树的根节点，
       则返回以 V 为根的树的节点数的负值。 */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        // 中文：在此处编写你的代码。
        return -1;
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    /* 中文：如果节点（顶点）V1 和 V2 连通，则返回 true。 */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        // 中文：在此处编写你的代码。
        return false;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    /* 中文：返回 V 所属集合的根节点。应使用路径压缩来加快查找；
       如果传入的元素无效，则抛出 IllegalArgumentException。 */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        // 中文：在此处编写你的代码。
        return -1;
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    /* 中文：通过合并 V1 和 V2 各自所属的集合来连接两个元素。V1 和 V2
       可以是任意元素，合并时使用“按大小合并”策略。如果两个集合一样大，
       将 V1 的根连到 V2 的根上。将元素与自身合并，或合并已连通的
       元素，都不应改变数据结构。 */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        // 中文：在此处编写你的代码。
    }

}
