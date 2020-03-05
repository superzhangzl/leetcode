package zzl.leetcode;

import org.junit.Assert;

/**
 * 加油站
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/gas-station/}
 */
public class GasStation {

    public static void main(String[] args) {
        int[] gas = new int[]{1, 2, 3, 4, 5};
        int[] cost = new int[]{3, 4, 5, 1, 2};
        Assert.assertEquals(new GasStation().canCompleteCircuit(gas, cost), 3);
        gas = new int[]{2, 3, 4};
        cost = new int[]{3, 4, 3};
        Assert.assertEquals(new GasStation().canCompleteCircuit(gas, cost), -1);

    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int length = gas.length;
        // 有先行条件，就不特殊判断了
        for (int i = 0; i < length; i++) {
            System.out.println("i=" + i);
            // 表示从i出发，行驶一圈回到i
            boolean flag = true;
            int capacity = 0;
            int start;
            for (start = i; flag || start % length != i; start++) {
                if (start > i) {
                    flag = false;
                }
                int index = start % length;
                System.out.print(index + " ");
                capacity += gas[index];
                // 满足条件就执行，不满足就跳出当前循环
                if (capacity < cost[index]) {
                    break;
                } else {
                    capacity -= cost[index];
                }
            }
            System.out.println();
            // 最后是行驶一圈回到i
            if (start % length == i && flag == false) {
                return i;
            }
        }
        return -1;
    }
}
