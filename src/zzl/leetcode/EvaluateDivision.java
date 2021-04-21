package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static zzl.base.enums.Difficulty.MEDIUM;

/**
 * 除法求值
 *
 * @author zzl
 * @date 2021-04-21
 * @link {https://leetcode-cn.com/problems/evaluate-division/}
 */
@Level(MEDIUM)
public class EvaluateDivision {
    public static void main(String[] args) {
        List<List<String>> equations;
        double[] values;
        List<List<String>> queries;
        double[] calcEquation;
        //
        equations = GenerateUtil.generateStringIntList("[[\"a\",\"b\"],[\"b\",\"c\"]]");
        values = GenerateUtil.generateDoubleArray("2.0,3.0");
        queries = GenerateUtil.generateStringIntList("[[\"a\",\"c\"],[\"b\",\"a\"],[\"a\",\"e\"],[\"a\",\"a\"],[\"x\",\"x\"]]");
        calcEquation = new EvaluateDivision().calcEquation(equations, values, queries);
        Assert.assertArrayEquals(calcEquation, new double[]{6.00000, 0.50000, -1.00000, 1.00000, -1.00000}, 0.0);
        //
        equations = GenerateUtil.generateStringIntList("[[\"a\",\"b\"],[\"b\",\"c\"],[\"bc\",\"cd\"]]");
        values = GenerateUtil.generateDoubleArray("1.5,2.5,5.0");
        queries = GenerateUtil.generateStringIntList("[[\"a\",\"c\"],[\"c\",\"b\"],[\"bc\",\"cd\"],[\"cd\",\"bc\"]]");
        calcEquation = new EvaluateDivision().calcEquation(equations, values, queries);
        Assert.assertArrayEquals(calcEquation, new double[]{3.75000, 0.40000, 5.00000, 0.20000}, 0.0);
        //
        equations = GenerateUtil.generateStringIntList("[[\"a\",\"b\"]]");
        values = GenerateUtil.generateDoubleArray("0.5");
        queries = GenerateUtil.generateStringIntList("[[\"a\",\"b\"],[\"b\",\"a\"],[\"a\",\"c\"],[\"x\",\"y\"]]");
        calcEquation = new EvaluateDivision().calcEquation(equations, values, queries);
        Assert.assertArrayEquals(calcEquation, new double[]{0.50000, 2.00000, -1.00000, -1.00000}, 0.0);
    }

    /**
     * 带权值的并查集
     * 注：必须做路径压缩到高度为2，这样两个节点之间的权值（即题目要求计算的除法求值）可以直接通过比值方式计算，两个节点之间共用一个节点做传递
     * 否则在计算时候必须考虑多级的路径传递权值关系
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     * @link {https://leetcode-cn.com/problems/evaluate-division/solution/399-chu-fa-qiu-zhi-nan-du-zhong-deng-286-w45d/}
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int equationsSize = equations.size();

        UnionFind unionFind = new UnionFind(2 * equationsSize);
        // 第 1 步：预处理，将变量的值与 id 进行映射，使得并查集的底层使用数组实现，方便编码
        Map<String, Integer> hashMap = new HashMap<>(2 * equationsSize);
        int id = 0;
        for (int i = 0; i < equationsSize; i++) {
            List<String> equation = equations.get(i);
            String var1 = equation.get(0);
            String var2 = equation.get(1);

            if (!hashMap.containsKey(var1)) {
                hashMap.put(var1, id);
                id++;
            }
            if (!hashMap.containsKey(var2)) {
                hashMap.put(var2, id);
                id++;
            }
            unionFind.union(hashMap.get(var1), hashMap.get(var2), values[i]);
        }
        // 第 2 步：做查询
        int queriesSize = queries.size();
        double[] res = new double[queriesSize];
        for (int i = 0; i < queriesSize; i++) {
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(1);

            Integer id1 = hashMap.get(var1);
            Integer id2 = hashMap.get(var2);

            if (id1 == null || id2 == null) {
                res[i] = -1.0d;
            } else {
                res[i] = unionFind.isConnected(id1, id2);
            }
        }
        return res;
    }

    class UnionFind {
        int[] parent;
        double[] weight;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.weight = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1.0d;
            }
        }

        public void union(int x, int y, double value) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            parent[rootX] = rootY;
            /*
             x --w[x]--> rx
             y --w[y]--> ry
             :
             rx --> ry
             :
             x --w[x]--> rx
               \            \w[rootX]
                y --w[y]--> ry

             */
            weight[rootX] = weight[y] * value / weight[x];
        }

        /**
         * 重点：在查询的时候，必须将路径高度压缩为2！！！！即连个节点连接同一个根节点(包括自己为根节点的情况)
         * 否在判断连通性时，无法直接使用比值的方式计算两个节点之间的权值。
         *
         * @param x
         * @return
         */
        private int find(int x) {
            if (x != parent[x]) {
                // 记录当前节点的父节点
                int origin = parent[x];
                // 找到父节点的根节点
                parent[x] = find(parent[x]);
                // 而且在union时候就会触发find，所以图整体高度不超过2，当新进来的时候，会出现第三层，在find时候被压缩
                // 所以此处乘一次就可以
                weight[x] *= weight[origin];
            }
            return parent[x];
        }

        private double isConnected(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                return -1.0d;
            } else {
                return weight[x] / weight[y];
            }
        }
    }
}
