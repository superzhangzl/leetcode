package zzl.leetcode;

import org.junit.Assert;

import java.util.Stack;

/**
 * 最小栈
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/min-stack/}
 */
public class MinStack {
    /**
     * 空间复杂度为O(2n)=O(n)，每次存储当前的最小值和输入数据
     * 使用额外的辅助栈也是这个空间复杂度
     */
    private Stack<Integer> stack = new Stack<>();
    private int min = Integer.MAX_VALUE;

    public MinStack() {
    }

    public void push(int x) {
        if (x <= min) {
            min = x;
        }
        stack.push(min);
        stack.push(x);

    }

    public void pop() {
        stack.pop();
        stack.pop();
        if (!stack.isEmpty()) {
            // 先临时pop出来，更新min，再塞回去
            Integer tmp = stack.pop();
            min = stack.peek();
            stack.push(tmp);
        } else {
            min = Integer.MAX_VALUE;
        }
    }

    //[null,null,null,null,2147483647,null,2147483646,null,2147483646,null,null,2147483647,2147483646,null,-2147483648,-2147483648,null,2147483646]
//[null,null,null,null,2147483647,null,2147483646,null,2147483646,null,null,2147483647,2147483647,null,-2147483648,-2147483648,null,2147483647]
    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }

    @Override
    public String toString() {
        return "MinStack{" +
                "stack=" + stack +
                ", min=" + min +
                '}';
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack);
        Assert.assertEquals(minStack.getMin(), -3);
        System.out.println(minStack);
        minStack.pop();
        System.out.println(minStack);
        Assert.assertEquals(minStack.top(), 0);
        System.out.println(minStack);
        Assert.assertEquals(minStack.getMin(), -2);
        System.out.println(minStack);
    }
}
