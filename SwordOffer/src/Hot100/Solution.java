package Hot100;

import java.util.*;

public class Solution {
    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     * 示例 1：
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     * 示例 2：
     * 输入：nums = [3,2,4], target = 6
     * 输出：[1,2]
     * 示例 3：
     * 输入：nums = [3,3], target = 6
     * 输出：[0,1]
     */
    public int[] twoSum(int[] nums, int target) {
        /*int[] res = new int[2];
        boolean flag = false;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i]+nums[j] == target){
                    res[0] = i;
                    res[1] = j;
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }
        return res;*/ // 暴力枚举，太费时间
        // todo 采用hash
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int newTarget = target - nums[i];
            if (map.containsKey(newTarget)) return new int[] {map.get(newTarget), i};
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 每个右括号都有一个对应的相同类型的左括号。
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
    public boolean isValid(String s) { // TODO: 2022/10/19
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{','}');
        map.put('[',']');
        map.put('?','?');
        Stack<Character> stack = new Stack<>();
        stack.push('?');

        if (s.length() > 0 && !map.containsKey(s.charAt(0))) return false; //字符串的第一个字符不是左括号，直接false

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) stack.push(c); // 当字符是左括号时，入栈
            else if (map.get(stack.pop()) != c) return false; // 字符不是左括号，且不是和栈顶元素构成对应关系的右括号，直接false；
        }
        return stack.size() == 1; // 执行到这里，如果是满足条件的字符串，此时栈内只有‘？’
    }

    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * 输入：l1 = [1,2,4], l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     * 输入：l1 = [], l2 = []
     * 输出：[]
     * 输入：l1 = [], l2 = [0]
     * 输出：[0]
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) { // TODO: 2022/10/19  居然双100%了
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        ListNode head = new ListNode(), tmp = head;
        while (list1 != null && list2 != null){
            if (list1.val >= list2.val){
                tmp.next = list2;
                tmp = tmp.next;
                list2 = list2.next;
            } else {
                tmp.next = list1;
                tmp = tmp.next;
                list1 = list1.next;
            }
        }
        if (list1 == null) tmp.next = list2;
        else tmp.next = list1;
        return head.next;
    }

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 输入：n = 2
     * 输出：2
     * 解释：有两种方法可以爬到楼顶。
     * 1. 1 阶 + 1 阶
     * 2. 2 阶
     * 输入：n = 3
     * 输出：3
     * 解释：有三种方法可以爬到楼顶。
     * 1. 1 阶 + 1 阶 + 1 阶
     * 2. 1 阶 + 2 阶
     * 3. 2 阶 + 1 阶
     */
    public int climbStairs(int n) { // 就是斐波那契数列变体，包括青蛙跳台阶
        if (n == 0 || n == 1) return 1;
        if (n == 2) return 2;
        int a = 1, b = 2, sum = 0;
        for (int i = 3; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return sum;
    }

    /**
     * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
     */
    public List<Integer> inorderTraversal(TreeNode root) { // 比官方题解居然还快
        if (root == null) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        List<Integer> lefts = inorderTraversal(root.left);
        res.addAll(lefts);
        res.add(root.val);
        List<Integer> rights = inorderTraversal(root.right);
        res.addAll(rights);
        return res;
    }

    /**
     * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
     */
    public boolean isSymmetric(TreeNode root) { // TODO: 2022/10/19 2种方式
        // 1. 队列
        /*if (root == null || (root.left == null && root.right == null)) return true;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()){
            TreeNode left = queue.removeFirst();
            TreeNode right = queue.removeFirst();

            if (left == null && right == null) continue;
            if (left == null || right == null) return false;
            if (left.val != right.val) return false;

            queue.add(left.left);
            queue.add(right.right);

            queue.add(left.right);
            queue.add(right.left);
        }
        return true;*/
        // 2. 递归 递归反而效率更高
        if (root == null) return true;
        return check(root.left, root.right); // 判断根节点的左右子树是否对称
    }

    private boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true; // 先判断当前两个子树的根节点是否满足对称条件
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;

        return check(left.left, right.right) && check(left.right, right.left); // 再递归判断左左和右右 左右和右左是否满足条件

    }
}
