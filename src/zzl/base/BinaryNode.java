package zzl.base;

/**
 * 也是node
 *
 * @author zzl
 */
public class BinaryNode {
    public int val;
    public BinaryNode left;
    public BinaryNode right;
    public BinaryNode next;

    public BinaryNode() {
    }

    public BinaryNode(int _val) {
        val = _val;
    }

    public BinaryNode(int _val, BinaryNode _left, BinaryNode _right, BinaryNode _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    public int getVal() {
        return val;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public BinaryNode getRight() {
        return right;
    }

    public BinaryNode getNext() {
        return next;
    }

    @Override
    public String toString() {
        return "{" +
                "\"val\":" + val +
                ", \"next\":" + next +
                ", \"left\":" + left +
                ", \"right\":" + right +
                '}';
    }
}