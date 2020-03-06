package zzl.leetcode;


import org.junit.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/best-sequence-at-index-lcci/}
 */
public class BestSequenceAtIndexLcci {
    public static void main(String[] args) {
//        Assert.assertEquals(bestSeqAtIndex(new int[]{65, 70, 56, 75, 60, 68}, new int[]{100, 150, 90, 190, 95, 110}), 6);
        Assert.assertEquals(bestSeqAtIndex(new int[]{2868, 5485, 1356, 1306, 6017, 8941, 7535, 4941, 6331, 6181},
                new int[]{5042, 3995, 7985, 1651, 5991, 7036, 9391, 428, 7561, 8594}), 5);
    }

    public static int bestSeqAtIndex(int[] height, int[] weight) {
        List<Pair> pairs = new ArrayList<>();
        List<Pair> pairs2 = new ArrayList<>();
        for (int i = 0; i < height.length; i++) {
            Pair pair = new Pair(height[i], weight[i]);
            pairs.add(pair);
            pairs2.add(pair);
        }
        pairs.sort(Comparator.comparingInt(Pair::getKey));
        System.out.println(pairs);
        pairs2.sort(Comparator.comparingInt(Pair::getValue));
        System.out.println(pairs2);
        int index1 = 0, index2 = 0, count = 0;
        while (index2 < pairs2.size()) {
            if (!pairs.contains(pairs2.get(index2))) {
                index1++;
                continue;
            }
            if (pairs.get(index1).getKey().equals(pairs2.get(index2).getKey())) {
                index1++;
                index2++;
                count++;
            } else {
                pairs2.remove(index2);
            }
        }
        return count;
    }

    static class Pair {

        Integer key;
        Integer value;

        public Pair(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public Integer getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
