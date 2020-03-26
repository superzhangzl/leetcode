package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.Arrays;

/**
 * 生存人数:
 * <p>
 * 给定N个人的出生年份和死亡年份，第i个人的出生年份为birth[i]，死亡年份为death[i]，实现一个方法以计算生存人数最多的年份。
 * <p>
 * 你可以假设所有人都出生于1900年至2000年（含1900和2000）之间。如果一个人在某一年的任意时期都处于生存状态，那么他们应该被纳入那一年的统计中。例如，生于1908年、死于1909年的人应当被列入1908年和1909年的计数。
 * <p>
 * 如果有多个年份生存人数相同且均为最大值，输出其中最小的年份。
 * <p>
 * 0 < birth.length == death.length <= 10000
 * birth[i] <= death[i]
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/living-people-lcci/}
 * @tag
 */
public class LivingPeopleLcci {
    public static void main(String[] args) {
        int[] birthInput = GenerateUtil.generateIntArray("1900,1901,1950", ",");
        int[] deathInput = GenerateUtil.generateIntArray("1948, 1951, 2000", ",");
        Assert.assertEquals(new LivingPeopleLcci().maxAliveYear(birthInput, deathInput), 1901);
        birthInput = GenerateUtil.generateIntArray("1972,1908,1915,1957,1960,1948,1912,1903,1949,1977,1900,1957,1934,1929,1913,1902,1903,1901", ",");
        deathInput = GenerateUtil.generateIntArray("1997,1932,1963,1997,1983,2000,1926,1962,1955,1997,1998,1989,1992,1975,1940,1903,1983,1969", ",");
        Assert.assertEquals(new LivingPeopleLcci().maxAliveYear(birthInput, deathInput), 1960);
    }

    /**
     * 然后设置两个指针指向出生序列和死亡序列，出生时间<=死亡时间时，总人口+1，否则-1；
     * 并且设置一个标记，记录总人口最大时的序列号。
     *
     * @param birth
     * @param death
     * @return
     * @link {https://leetcode-cn.com/problems/living-people-lcci/solution/shuang-zhi-zhen-by-qi-feng-jue/}
     */
    public int maxAliveYear(int[] birth, int[] death) {
        int i = 0; // 游标
        int j = 0;//游标
        int sum = 0;//记录当年存活人数
        int max = 0;//记录最大存活人数
        int flag = 0;//记录最大存活人数所在的年份的下标
        Arrays.sort(birth);
        Arrays.sort(death);
        PrintConsoleUtil.printArray(birth);
        PrintConsoleUtil.printArray(death);
        /*
        题目的隐含条件：birth[i] <= death[i]，所以while循环肯定可以跳出的，不会有人提前挂掉的
         */
        while (i < birth.length) {
            if (birth[i] <= death[j]) {
                // 今年多活了一个
                sum++;
                if (max < sum) {
                    max = sum;
                    flag = i;
                }
                i++;
            } else {
                j++;
                // 挂了一个
                sum--;
            }
            System.out.println(String.format("i=%d, j=%d, sum=%d", i, j, sum));
        }
        return birth[flag];
    }
}
