package LeetCodeByClass.DataStructure.StackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列实现栈
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）
 * 注意：
 *  你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
 *  你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 */
public class MyStack {

    Queue<Integer> queue;

    public MyStack() {
        queue = new LinkedList<>();
    }

    public void push(int x) { // 将元素 x 压入栈顶
        // TODO: 2022/12/23 在将一个元素 x 插入队列时，为了维护原来的后进先出顺序，需要让 x 插入队列首部。
        //  而队列的默认插入顺序是队列尾部，因此在将 x 插入队列尾部之后，需要让除了 x 之外的所有元素出队列，再入队列
        queue.offer(x);
        int len = queue.size();
        while (len-- > 1){
            queue.offer(queue.poll());
        }
    }

    public int pop() { // 移除并返回栈顶元素
        return queue.remove();
    }

    public int top() { // 返回栈顶元素
        return queue.peek();
    }

    public boolean empty() { // 如果栈是空的，返回 true ；否则，返回 false
        return queue.isEmpty();
    }
}
