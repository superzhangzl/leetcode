package zzl.leetcode;

import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 有序矩阵中的第 k 个最小数组和
 * 给你一个 m * n 的矩阵 mat，以及一个整数 k ，矩阵中的每一行都以非递减的顺序排列。
 * <p>
 * 你可以从每一行中选出 1 个元素形成一个数组。返回所有可能数组中的第 k 个 最小 数组和。
 * <p>
 * zzl：非递减的顺序排列是什么鬼？？？非递减就是严格的递增？不算乱序？辣鸡描述
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/}
 */
public class FindTheKthSmallestSumOfAMatrixWithSortedRows {
    public static void main(String[] args) {
        int[][] array = GenerateUtil.generateBinaryIntArrayBetter("[[1,3,11],[2,4,6]]");
        PrintConsoleUtil.printArray(array);
        int kthSmallest = new FindTheKthSmallestSumOfAMatrixWithSortedRows().kthSmallest(array, 5);
        System.out.println(kthSmallest);
    }

    int cnt;

    /**
     * @param mat
     * @param k
     * @return
     * @link {https://leetcode-cn.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/solution/find-the-kth-smallest-sum-of-a-matrix-by-ikaruga/}
     */
    public int kthSmallest(int[][] mat, int k) {
        int leftMin = 0;
        int rightMax = 0;
        // 最大值和最小值
        for (int i = 0; i < mat.length; i++) {
            leftMin += mat[i][0];
            rightMax += mat[i][mat[i].length - 1];
        }
        System.out.println(leftMin);
        System.out.println(rightMax);
        System.out.println();
        int base = leftMin;

        // 以最小值和最大值做二分查找
        /*
        采用二分法。这里二分的是“值”，而不是“索引”。
        初始化left, right = sum(matrix[*][0]), sum(matrix[*][-1])，那么第k小的数组和一定在[left, right]中。
        我们对这个区间进行二分，计算小于等于mid的数的个数count。
            如果k > count，那么第k小的组和一定属于(mid, right]区间
            如果k <= count，那么第k小的数组和一定属于[left, mid]区间

        不断缩小区间，当left == right时，就找到了答案。
        代码中，函数dfs方法在矩阵mat找小于等于mid的个数count并返回。注意，当count > k时，及时返回，否则会超时。
         */
        while (leftMin < rightMax) {
            // 中间值而不是中间索引
            int mid = leftMin + (rightMax - leftMin) / 2;
            cnt = 1;
            // 从第0行开始dfs
            // base是最小值
            dfs(mat, k, mid, 0, base);
            if (cnt < k) {
                leftMin = mid + 1;
            } else {
                rightMax = mid;
            }
        }
        return rightMax;
    }

    private void dfs(int[][] mat, int k, int maxSum, int idx, int sum) {
        // 递归触底退出
        if (idx == mat.length) {
            return;
        }
        // 剪枝
        if (sum > maxSum || cnt > k) {
            return;
        }
        // 递归向下一行
        dfs(mat, k, maxSum, idx + 1, sum);

        // “当前”行右移
        for (int j = 1; j < mat[idx].length; j++) {
            int temp = sum + mat[idx][j] - mat[idx][0];
            // 剪枝
            if (temp > maxSum) {
                break;
            }
            cnt++;
            // 如果是最后一行往下递归会退出；如果不是最后一行，会向右平移接着往下递归
            dfs(mat, k, maxSum, idx + 1, temp);
        }
    }
}
