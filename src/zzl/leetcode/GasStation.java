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

    /**
     * @param gas
     * @param cost
     * @return
     * @link {https://leetcode-cn.com/problems/gas-station/solution/jia-you-zhan-by-leetcode/}
     */
    private int canCompleteCircuit(int[] gas, int[] cost) {
        int length = gas.length;
        // 总的储油量
        int totalTank = 0;
        // 当前起点的储油量
        int currentTank = 0;
        // 记录起始位置
        int startStation = 0;
        for (int i = 0; i < length; i++) {
            totalTank += gas[i] - cost[i];
            currentTank += gas[i] - cost[i];
            // 如果从startStation开始的形成油量不够，那就从下一个位置开始
            // 把当前的油量置为0
            // 但总的total继续累加，只有总的剩余油量大于等于0，说明才能返回起点
            // 只要从startStation~length可达，那从0~startStation也可达，证明过程详见链接
            if (currentTank < 0) {
                startStation = i + 1;
                currentTank = 0;
            }

        }
        return totalTank >= 0 ? startStation : -1;
    }

    /**
     * 最基础的第一想法，但是时间复杂度高，O(n^2)
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuitStupid(int[] gas, int[] cost) {
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
