package LeetCodeByClass.DataStructure.StackAndQueue;

import java.util.*;

public class Solution {
    /**
     * 1. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     *  左括号必须用相同类型的右括号闭合。
     *  左括号必须以正确的顺序闭合。
     *  每个右括号都有一个对应的相同类型的左括号。
     * 示例 1：
     * 输入：s = "()"
     * 输出：true
     * 示例 2：
     * 输入：s = "()[]{}"
     * 输出：true
     * 示例 3：
     * 输入：s = "(]"
     * 输出：false
     */
    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        Deque<Character> stack = new LinkedList<>();
        stack.push('?'); // 实现放一个别的符号进去，防止 39 行出现空栈的pop
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)){
                stack.push(map.get(c));
            }else {
                Character ch = stack.peek();
                if (ch == c){
                    stack.pop();
                }else {
                    return false;
                }
            }
        }
        return stack.size() == 1;
    }

    /**
     * 2. 每日温度
     * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。
     * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
     * 示例 1:
     * 输入: temperatures = [73,74,75,71,69,72,76,73]
     * 输出: [1,1,4,2,1,1,0,0]
     * 示例 2:
     * 输入: temperatures = [30,40,50,60]
     * 输出: [1,1,1,0]
     * 示例 3:
     * 输入: temperatures = [30,60,90]
     * 输出: [1,1,0]
     */
    public int[] dailyTemperatures(int[] temperatures) {
        /*int[] ans = new int[temperatures.length];
        int count = 1;
        for (int i = 0; i < temperatures.length - 1; i++) {
            int tempIndex = i + 1;
            while (tempIndex < temperatures.length && temperatures[tempIndex] <= temperatures[i]){
                count++;
                tempIndex++;
            }
            if (tempIndex == temperatures.length) ans[i] = 0;
            else ans[i] = count;
            count = 1;
        }
        return ans;*/ // 超出时间限制
        // TODO: 2022/12/24 在遍历数组时用栈把数组中的数存起来，如果当前遍历的数比栈顶元素大，说明栈顶元素的下一个比它大的数就是当前元素。
        //  单调栈
        int len = temperatures.length;
        int[] ans = new int[len];
        Deque<Integer> stack = new LinkedList<>(); // 存数的索引
        for (int curIndex = 0; curIndex < len; curIndex++) {
            while (!stack.isEmpty() && temperatures[curIndex] > temperatures[stack.peek()]){
                int preIndex = stack.pop();
                ans[preIndex] = curIndex - preIndex;
            }
            stack.push(curIndex);
        }
        return ans;
    }

    /**
     * 3. 下一个更大元素 II
     * 给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素 。
     * 数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1 。
     * 示例 1:
     * 输入: nums = [1,2,1]
     * 输出: [2,-1,2]
     * 解释: 第一个 1 的下一个更大的数是 2；
     * 数字 2 找不到下一个更大的数；
     * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
     * 示例 2:
     * 输入: nums = [1,2,3,4,3]
     * 输出: [2,3,4,-1,4]
     */
    public int[] nextGreaterElements(int[] nums) { // TODO: 2022/12/24 单调栈
        int length = nums.length;
        int[] ans = new int[length];
        Arrays.fill(ans, -1);
        Deque<Integer> pre = new LinkedList<>();
        for (int i = 0; i < length * 2 - 1; i++) { // 2length-1 是相当于把循环数组拉直的最大长度，实际处理的时候不需要另外存储，只需要对下标取余，就能在逻辑上做到把数组拉直
            int cirIndex = i % length;
            int curNum = nums[cirIndex];
            while (!pre.isEmpty() && nums[pre.peek()] < curNum) {
                ans[pre.pop()] = curNum;
            }
            pre.push(cirIndex);
        }
        return ans;
    }
}
