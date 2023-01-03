package SwordByClass.StackAndQueue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Solution {
    /**
     * 31. 栈的压入、弹出序列
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
     * 假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
     * 示例 1：
     * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
     * 输出：true
     * 解释：我们可以按以下顺序执行：
     * push(1), push(2), push(3), push(4), pop() -> 4,
     * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
     * 示例 2：
     * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
     * 输出：false
     * 解释：1 不能在 2 之前弹出。
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        // TODO: 2023/1/3 使用一个辅助栈来模拟入栈和出栈
        Deque<Integer> stack = new LinkedList<>();
        int i = 0;
        for (int num : pushed) { // 对于要入栈的每一个元素
            stack.push(num); // 入栈
            while (!stack.isEmpty() && stack.peek()==popped[i]){ // 栈不为空并且当前栈顶的元素和出栈队列中的元素相同
                stack.pop(); // 出栈
                i++; // 循环判断出栈队列的下一个元素是否和当前的栈顶元素相同，也就是判断是否要进行出栈操作
            }
        }
        return stack.isEmpty(); // 如果是满足要求的出栈队列，最后栈内应该为空
    }

    /**
     * 40. 最小的k个数
     * 输入整数数组 arr ，找出其中最小的 k 个数。
     * 例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     * 示例 1：
     * 输入：arr = [3,2,1], k = 2
     * 输出：[1,2] 或者 [2,1]
     * 示例 2：
     * 输入：arr = [0,1,2,1], k = 1
     * 输出：[0]
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        /*PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : arr) {
            queue.add(num);
        }
        int[] ans = new int[k];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = queue.remove();
        }
        return ans;*/
        // TODO: 2023/1/3 最朴素的想法就是先排序，然后返回前k个数，使用快速排序实现：哨兵划分+递归
        /*quickSort(arr, 0, arr.length-1);
        return Arrays.copyOf(arr, k);*/
        // 更进一步，没必要获取整个数组，题目只要求返回最小的 k 个数，对这 k 个数的顺序并没有要求。
        // 因此，只需要将数组划分为 最小的 k 个数 和 其他数字 两部分即可，而快速排序的哨兵划分可完成此目标。
        // 根据快速排序原理，如果某次哨兵划分后 基准数正好是第 k+1 小的数字 ，那么此时基准数左边的所有数字便是题目所求的 最小的 k 个数 。
        // 根据此思路，考虑在每次哨兵划分后，判断基准数在数组中的索引是否等于 k ，若 true 则直接返回此时数组的前 k 个数字即可。
        if (k >= arr.length) return arr;
        quickSort2(arr, k, 0, arr.length-1);
    }

    private int[] quickSort2(int[] arr, int k, int left, int right) {
        // 哨兵划分的时候不变
        int i = left, j = right;
        while (i < j){
            while (i < j && arr[j] >= arr[left]) j--;
            while (i < j && arr[i] <= arr[left]) i++;
            swap(arr, i, j);
        }
        swap(arr, left, i);
        // 递归的时候，判断当前基准的索引和k的关系
        if (i>k) quickSort2(arr, k, left, i-1);
        if (i<k) quickSort2(arr, k, i+1, right);
        return Arrays.copyOf(arr,k);
    }

    private void quickSort(int[] arr, int left, int right) {
        if (left > right) return; // 递归终止条件
        // 以arr[left]为哨兵，比它小的都放到他左边，大的放右边
        int i = left, j = right;
        while (i < j){
            while (i < j && arr[j] >= arr[left]) j--;
            while (i < j && arr[i] <= arr[left]) i++;
            swap(arr, i, j);
        }
        swap(arr, left, i);
        quickSort(arr, left, i-1);
        quickSort(arr, i+1, right);
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
