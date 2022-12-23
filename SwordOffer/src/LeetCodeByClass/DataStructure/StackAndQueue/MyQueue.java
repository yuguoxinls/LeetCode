package LeetCodeByClass.DataStructure.StackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 用栈实现队列
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）
 * 说明：
 *  你 只能 使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 *  你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 */
public class MyQueue {

    /*Deque<Integer> s1;
    Deque<Integer> s2;

    public MyQueue() {
        s1 = new LinkedList<>();
        s2 = new LinkedList<>();
    }

    public void push(int x) { // 将元素 x 推到队列的末尾
        s1.push(x);
    }

    public int pop() { // 从队列的开头移除并返回元素
        while (!s1.isEmpty()){
            Integer val = s1.pop();
            s2.push(val);
        }
        Integer ans = s2.pop();
        while (!s2.isEmpty()){
            Integer val = s2.pop();
            s1.push(val);
        }
        return ans;
    }

    public int peek() { // 返回队列开头的元素
        while (!s1.isEmpty()){
            Integer val = s1.pop();
            s2.push(val);
        }
        Integer ans = s2.peek();
        while (!s2.isEmpty()){
            Integer val = s2.pop();
            s1.push(val);
        }
        return ans;
    }

    public boolean empty() { // 如果队列为空，返回 true ；否则，返回 false
        return s1.isEmpty();
    }*/
    // TODO: 2022/12/23 不用每次pop的时候，都倒一遍数据，将一个栈当作输入栈，用于压入 push 传入的数据；另一个栈当作输出栈，用于 pop 和 peek 操作。
    Deque<Integer> inStack;
    Deque<Integer> outStack;

    public MyQueue() {
        inStack = new ArrayDeque<Integer>();
        outStack = new ArrayDeque<Integer>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        if (outStack.isEmpty()) {
            in2out();
        }
        return outStack.pop();
    }

    public int peek() {
        if (outStack.isEmpty()) {
            in2out();
        }
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    private void in2out() { // 把输入栈的数据倒进输出栈
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
    }
}
