package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 拼车
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/car-pooling/}
 */
public class CarPooling {
    public static void main(String[] args) {
        int[][] input = GenerateUtil.generateBinaryIntArray("2,1,5\n" +
                "3,3,7", ",");
        Assert.assertEquals(new CarPooling().carPooling(input, 4), false);
        input = GenerateUtil.generateBinaryIntArray("2,1,5\n" +
                "3,3,7", ",");
        Assert.assertEquals(new CarPooling().carPooling(input, 5), true);
        input = GenerateUtil.generateBinaryIntArray("2,1,5\n" +
                "3,5,7", ",");
        Assert.assertEquals(new CarPooling().carPooling(input, 3), true);
        input = GenerateUtil.generateBinaryIntArray("3,2,7\n" +
                "3,7,9\n" +
                "8,3,9", ",");
        Assert.assertEquals(new CarPooling().carPooling(input, 11), true);
        input = GenerateUtil.generateBinaryIntArray("3,5,9\n" +
                "4,2,5\n" +
                "3,4,6\n" +
                "9,1,4\n" +
                "5,6,8\n" +
                "5,4,6", ",");
        Assert.assertEquals(new CarPooling().carPooling(input, 14), true);
    }

    /**
     * 可参考{@link LivingPeopleLcci}
     *
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPooling(int[][] trips, int capacity) {
        // 记录这上车人数和上车点
        int[][] getOn = new int[trips.length][2];
        // 记录这下车人数和下车点
        int[][] getOff = new int[trips.length][2];
        for (int i = 0; i < trips.length; i++) {
            getOn[i][0] = trips[i][0];
            getOn[i][1] = trips[i][1];
            getOff[i][0] = trips[i][0];
            getOff[i][1] = trips[i][2];
        }
        //按照上下车点排序
        Arrays.sort(getOn, Comparator.comparingInt(o -> o[1]));
        Arrays.sort(getOff, Comparator.comparingInt(o -> o[1]));
        int i = 0; // 游标
        int j = 0;//游标
        int currentPeopleCount = 0; // 记录当前在车上的人数
        int maxPeopleCountInCar = 0;// 记录在车上的最大人数
        while (i < trips.length) {
            // 这里的比较条件不能是<=，生存人数那道题里的出生年份和死亡年份是算在统计内的，
            // 但是这里的在同一站不是上车就是下车，不能算在统计内
            // 隐含条件也是，要么上车，要么下车，下车肯定是晚于上车的
            // 比较上下车点的位置，如果是上车，就新增乘客人数，如果是下车就减去乘客人数
            if (getOn[i][1] < getOff[j][1]) {
                // 增加当前位置的上车人数
                currentPeopleCount += getOn[i][0];
                if (maxPeopleCountInCar < currentPeopleCount) {
                    maxPeopleCountInCar = currentPeopleCount;
                }
                i++;
            } else {
                // 减去当前位置的下车人数
                currentPeopleCount -= getOff[j][0];
                j++;
            }
            System.out.println(String.format("i=%d, j=%d, currentPeopleCount=%d", i, j, currentPeopleCount));
        }
        System.out.println();
        return maxPeopleCountInCar <= capacity;
    }
}
