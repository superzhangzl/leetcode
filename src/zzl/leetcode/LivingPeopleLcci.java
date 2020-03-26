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
 * 你可以假设所有人都出生于1900年至2000年（含1900和2000）之间。如果一个人在某一年的任意时期都处于生存状态，那么他们应该被纳入那一年的统计中。
 * 例如，生于1908年、死于1909年的人应当被列入1908年和1909年的计数。
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
//        Assert.assertEquals(new LivingPeopleLcci().maxAliveYear(birthInput, deathInput), 1901);
        birthInput = GenerateUtil.generateIntArray("1972,1908,1915,1957,1960,1948,1912,1903,1949,1977,1900,1957,1934,1929,1913,1902,1903,1901", ",");
        deathInput = GenerateUtil.generateIntArray("1997,1932,1963,1997,1983,2000,1926,1962,1955,1997,1998,1989,1992,1975,1940,1903,1983,1969", ",");
//        Assert.assertEquals(new LivingPeopleLcci().maxAliveYear(birthInput, deathInput), 1960);
        birthInput = GenerateUtil.generateIntArray("1927,1954,1903,1928,1956,1929,1929,1928,1958,1902,1953,1912,1923,1937,1915,1942,1964,1954,1924,1936,1963,1950,1904,1931,1951,1953,1922,1970,1986,1947,1911,1965,1913,1920,1947,1962,1903,1905,1978,1974,1950,1921,1929,1901,1925,1929,1951,1944,1945", ",");
        deathInput = GenerateUtil.generateIntArray("1987,1992,1967,1997,1963,1970,1944,1986,1997,1937,1971,1982,1980,1992,1995,1992,1991,1964,1985,1938,1975,1964,1975,1961,1995,1985,1946,1989,1999,1994,1956,1984,1999,1966,1950,1993,1960,1939,1990,1975,1982,1921,1964,1998,1969,1970,1965,1973,1958", ",");
        Assert.assertEquals(new LivingPeopleLcci().maxAliveYear(birthInput, deathInput), 1956);
    }

    public int maxAliveYear(int[] birth, int[] death) {
        int[] lives = new int[1001];
        int[] gos = new int[1001];
        for (int val : birth) lives[val - 1900]++;
        for (int val : death) gos[val - 1900]++;
        for (int i = 1; i < 1001; i++) lives[i] = lives[i - 1] + lives[i];
        for (int i = 1; i < 1001; i++) gos[i] = gos[i - 1] + gos[i];
        int max_p = lives[0];
        int year = 0;
        for (int i = 1; i < 1001; i++) {
            // 计算当年生存的人数
            int p = lives[i] - gos[i - 1];
            if (max_p < p) {
                max_p = p;
                year = i;
            }
        }
        return 1900 + year;

    }

    /**
     * 参考{@link CarPooling carPooling()} 的错误示例，参考中的是不能共存的，要么上车要么下车，就是说用户在同一个节点只有一个状态
     * 但是该题目中，用户出生在x年，在x年死亡，还是需要纳入当年的统计之中。
     * 所以这个生存和死亡状态是需要区分开统计，再进行判断当年存活人数
     *
     * @param birth
     * @param death
     * @return
     */
    @Deprecated
    public int maxAliveYearError(int[] birth, int[] death) {
        int[] cnt = new int[101];
        for (int i = 0; i < birth.length; i++) {
            cnt[birth[i] - 1900]++;
            cnt[death[i] - 1900]--;
        }
        int maxCountYear = 0;
        int maxCount = cnt[0];
        for (int i = 1; i < cnt.length; i++) {
            cnt[i] += cnt[i - 1];
            if (maxCount < cnt[i]) {
                maxCountYear = i;
                maxCount = cnt[i];

            }
//            System.out.println(String.format("i=%3d, cnt[%2d]=%2d, maxCount=%2d", i, i, cnt[i], maxCount));
        }
        return maxCountYear + 1900;
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
    public int maxAliveYear1(int[] birth, int[] death) {
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
