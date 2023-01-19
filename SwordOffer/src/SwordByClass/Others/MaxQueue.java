package SwordByClass.Others;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 59. 队列的最大值
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，
 * 要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 */
public class MaxQueue {
    /*Queue<Integer> queue;
    PriorityQueue<Integer> priorityQueue;
    public MaxQueue() {
        queue = new LinkedList<>();
        priorityQueue = new PriorityQueue<>(((o1, o2) -> o2-o1));
    }

    public int max_value() {
        if (queue.isEmpty()) return -1;
        return priorityQueue.peek();
    }

    public void push_back(int value) {
        queue.add(value);
        priorityQueue.add(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) return -1;
        Integer poll = queue.poll();
        priorityQueue.remove(poll);
        return poll;
    }*/ // 太慢
    // TODO: 2023/1/19 k神 使用一个单调不增队列来维护最大值
    // 使用双向队列来实现
    Queue<Integer> queue;
    Deque<Integer> deque; // 首元素是队列中的最大元素
    public MaxQueue() {
        queue = new LinkedList<>();
        deque = new LinkedList<>();
    }
    public int max_value() {
        return deque.isEmpty() ? -1 : deque.peekFirst();
    }
    public void push_back(int value) {
        queue.offer(value);
        while(!deque.isEmpty() && deque.peekLast() < value) {
            // 把单调不增队列中比value小的数全部弹出
            deque.pollLast();
        }
        deque.offerLast(value);
    }
    public int pop_front() {
        if(queue.isEmpty()) return -1;
        if(queue.peek().equals(deque.peekFirst()))
            deque.pollFirst();
        return queue.poll();
    }
}
