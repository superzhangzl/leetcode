package zzl.leetcode;

import org.junit.Assert;

/**
 * [卡特兰数](http://lanqi.org/skills/10939/)
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/unique-binary-search-trees/}
 */
public class UniqueBinarySearchTrees {
    public static void main(String[] args) {
        Assert.assertEquals(new UniqueBinarySearchTrees().numTrees(3), 5);
    }

    /**
     * 与UniqueBinarySearchTreesII 的需要数据所有结果不同，该题只需要统计次数
     *
     * @param n
     * @return
     * @link {https://leetcode-cn.com/problems/unique-binary-search-trees/solution/bu-tong-de-er-cha-sou-suo-shu-by-leetcode/}
     */
    public int numTrees(int n) {
        // 卡特兰数，n=0,时C_n=1
        /*
        递推公式：
        C_{n+1} = 2(2n+1)/(n+2)*C_n
         */
        // 使用卡特兰数计算时间短但是内存消耗反而比较大
        long C = 1;
        for (int i = 1; i < n; i++) {
            int cur = (2 * (2 * i + 1)) / (i + 2);
            System.out.println(i);
            System.out.println(cur);
            System.out.println(C);
            // 乘除的顺序差异会导致不同的结果
            System.out.println(C * (2 * (2 * i + 1)) / (i + 2));
            System.out.println(C * ((2 * (2 * i + 1)) / (i + 2)));
            C = C * (2 * (2 * i + 1)) / (i + 2);
            System.out.println(C);
            System.out.println();
            // 下面的乘数顺序会导致计算失败
//            C = ((2 * (2 * i + 1)) / (i + 2)) * C;
        }
        return (int) C;
    }
}
