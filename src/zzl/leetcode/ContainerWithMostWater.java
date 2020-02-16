package zzl.leetcode;

import org.junit.Assert;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/container-with-most-water/}
 */
public class ContainerWithMostWater {
    public static void main(String[] args) {
        Assert.assertEquals(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}), 49);
    }

    /**
     * 证明方法参考：
     * 即每次只需要移动最短的指针，以短的那一端为边界不动，移动高的，i,j区域内的所有的都不会比这个大
     *
     * @param height
     * @return
     * @link {https://leetcode-cn.com/problems/container-with-most-water/solution/shuang-zhi-zhen-fa-zheng-que-xing-zheng-ming-by-r3/}
     */
    public static int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int leftIndex = 0;
        int rightIndex = height.length - 1;
        int maxArea = 0;
        while (leftIndex <= rightIndex) {
            int area = Math.min(height[leftIndex], height[rightIndex]) * (rightIndex - leftIndex);
//            System.out.println("currentIndex: " + leftIndex + ", " + rightIndex);
//            System.out.println("currentArea: " + area);
            if (area > maxArea) {
                maxArea = area;
            }
            if (height[leftIndex] <= height[rightIndex]) {
                leftIndex++;
            } else {
                rightIndex--;
            }
        }
        return maxArea;
    }
}
