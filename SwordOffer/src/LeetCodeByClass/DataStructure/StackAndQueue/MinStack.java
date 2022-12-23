package LeetCodeByClass.DataStructure.StackAndQueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

/**
 * 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 */
public class MinStack {

    Deque<Integer> stack;
    Deque<Integer> minStack;

    public MinStack() { // 初始化堆栈对象
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int val) { // 将元素val推入堆栈
        stack.push(val);
        if (val <= minStack.peek()) minStack.push(val);
    }

    public void pop() { // 删除堆栈顶部的元素
        Integer val = stack.pop();
        if (Objects.equals(val, minStack.peek())) minStack.pop();
    }

    public int top() { // 获取堆栈顶部的元素
        return stack.peek();
    }

    public int getMin() { // 获取堆栈中的最小元素
        return minStack.peek();
    }
}
