package zzl.base;

import java.util.Optional;

/**
 * @author zzl
 */
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    public int getVal() {
        return val;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Node getNext() {
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