package zzl.leetcode;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的日程安排表 II
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/my-calendar-ii/}
 */
public class MyCalendarII {
    public static void main(String[] args) {
        MyCalendarTwo calendar = new MyCalendarTwo();
        Assert.assertTrue(calendar.book(10, 20)); // returns true
        Assert.assertTrue(calendar.book(50, 60)); // returns true
        Assert.assertTrue(calendar.book(10, 40)); // returns true
        Assert.assertFalse(calendar.book(5, 15)); // returns false
        Assert.assertTrue(calendar.book(5, 10)); // returns true
        Assert.assertTrue(calendar.book(25, 55)); // returns true

    }
}

class MyCalendarTwo {
    private List<Pair> one = new ArrayList<>();
    private List<Pair> two = new ArrayList<>();

    public MyCalendarTwo() {

    }

    public boolean book(int start, int end) {
        for (Pair pair : two) {
            if (pair.start < end && start < pair.end) {
                return false;
            }
        }
        for (Pair pair : one) {
            if (pair.start < end && start < pair.end) {
                two.add(new Pair(Math.max(pair.start, start), Math.min(pair.end, end)));
            }
        }
        one.add(new Pair(start, end));
        return true;
    }

    class Pair {
        int start;
        int end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}