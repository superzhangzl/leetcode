package zzl.base.templates;

/**
 * 并查集模板
 *
 * @author zzl
 * @date 2020-12-01
 */
public class UnionFind {
    // 连通分量的个数
    private int count;
    // 节点i的父节点下标
    private int[] parent;
    // 节点i可到达的节点的个数，即节点的重量
    private int[] size;

    public UnionFind(int n) {
        this.count = n;
        this.parent = new int[n];
        this.size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

    }

    /**
     * 判断两个节点是否相连，就是判断他俩的根节点是不是同一个
     *
     * @param p
     * @param q
     * @return
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 将p和q连接在一起，并按照权重进行平衡
     *
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        // 如果P的权重大，就把q接在p上
        if (size[rootP] > size[rootQ]) {
            // 将Q的根节点接在P的根节点上，
            parent[rootQ] = rootP;
            // 将q的权重累加给P
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        // 连通分量减一
        count--;
    }

    /**
     * 寻找节点x的根节点
     */
    public int find(int x) {
        // 根节点的父节点是自己
        while (parent[x] != x) {
            // 同时平衡树的高度
            parent[x] = parent[parent[x]];
            // 找到x的父元素
            x = parent[x];
        }
        return x;

    }

    public int getCount() {
        return count;
    }
}
